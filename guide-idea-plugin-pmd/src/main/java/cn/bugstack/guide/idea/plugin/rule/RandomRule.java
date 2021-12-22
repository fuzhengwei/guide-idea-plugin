package cn.bugstack.guide.idea.plugin.rule;

import com.intellij.codeInsight.FileModificationService;
import com.intellij.codeInsight.daemon.impl.actions.AddImportAction;
import com.intellij.codeInspection.*;
import com.intellij.openapi.application.ApplicationManager;
import com.intellij.openapi.editor.Document;
import com.intellij.openapi.editor.Editor;
import com.intellij.openapi.editor.EditorFactory;
import com.intellij.openapi.project.Project;
import com.intellij.psi.*;
import com.intellij.psi.search.PsiShortNamesCache;
import com.intellij.util.ObjectUtils;
import org.jetbrains.annotations.Nls;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;

/**
 * ref: https://rules.sonarsource.com/java/type/Security%20Hotspot/RSPEC-2245
 */
public class RandomRule extends AbstractBaseJavaLocalInspectionTool {

    @Override
    public @NotNull PsiElementVisitor buildVisitor(@NotNull ProblemsHolder holder, boolean isOnTheFly, @NotNull LocalInspectionToolSession session) {
        return new JavaElementVisitor() {
            @Override
            public void visitNewExpression(PsiNewExpression expression) {
                if ("java.util.Random".equals(Objects.requireNonNull(expression.getClassReference()).getQualifiedName())) {
                    holder.registerProblem(expression,
                            "Unsafe pseudorandom generator used",
                            ProblemHighlightType.GENERIC_ERROR_OR_WARNING,
                            new ReplacePseudorandomGeneratorQuickFix()
                    );
                }
            }
        };
    }

    public static class ReplacePseudorandomGeneratorQuickFix implements LocalQuickFix {
        @Override
        public @Nls(capitalization = Nls.Capitalization.Sentence) @NotNull String getFamilyName() {
            return "!Fix：replace by SecureRandom";
        }

        @Override
        public void applyFix(@NotNull Project project, @NotNull ProblemDescriptor descriptor) {
            PsiNewExpression newExp = ObjectUtils.tryCast(descriptor.getPsiElement(), PsiNewExpression.class);
            if (newExp == null) {
                return;
            }

            PsiElement parent = newExp.getParent();
            PsiTypeElement typeElement = null;
            if (parent instanceof PsiAssignmentExpression) {
                // 变量初始化, parent指向声明点
                PsiAssignmentExpression assignmentExpression = ObjectUtils.tryCast(parent, PsiAssignmentExpression.class);
                if (assignmentExpression != null) {
                    PsiReference lRef = assignmentExpression.getLExpression().getReference();
                    if (lRef != null) {
                        parent = lRef.resolve();
                    }
                }
            }

            if (parent instanceof PsiLocalVariable) {
                // 变量声明同时初始化
                PsiLocalVariable localVariable = ObjectUtils.tryCast(parent, PsiLocalVariable.class);
                if (localVariable != null) {
                    typeElement = localVariable.getTypeElement();
                }
            } else if (parent instanceof PsiField) {
                // field 变量
                PsiField field = ObjectUtils.tryCast(parent, PsiField.class);
                if (field != null) {
                    typeElement = field.getTypeElement();
                }
            }

            if (typeElement == null) {
                return;
            }

            PsiElementFactory factory = JavaPsiFacade.getElementFactory(project);
            typeElement.replace(factory.createTypeElementFromText("SecureRandom", null));
            PsiNewExpression secureNewExp = (PsiNewExpression) factory.createExpressionFromText("new SecureRandom()", null);
            newExp.replace(secureNewExp);

            // point NewExpression to element in file
            secureNewExp = ObjectUtils.tryCast(((PsiVariable) parent).getInitializer(), PsiNewExpression.class);
            if (secureNewExp == null) return;

            // import java.security.SecureRandom
            try {
                PsiFile file = descriptor.getPsiElement().getContainingFile();
                Document document = PsiDocumentManager.getInstance(project).getDocument(file);
                PsiJavaCodeReferenceElement secureRefElem = secureNewExp.getClassOrAnonymousClassReference();

                if (document != null && secureRefElem != null) {
                    Editor[] editors = EditorFactory.getInstance().getEditors(document, project);
                    if (!FileModificationService.getInstance().prepareFileForWrite(file)) return;
                    if (secureRefElem.getReferenceName() == null) return;

                    ApplicationManager.getApplication().runWriteAction(() -> {
                        PsiClass[] classes = PsiShortNamesCache.getInstance(project)
                                .getClassesByName(secureRefElem.getReferenceName(), secureRefElem.getResolveScope());
                        for (PsiClass clazz : classes) {
                            if ("java.security.SecureRandom".equals(clazz.getQualifiedName())) {
                                (new AddImportAction(project, secureRefElem, editors[0], new PsiClass[]{clazz}) {
                                    @Override
                                    protected void bindReference(PsiReference ref, PsiClass targetClass) {
                                        ref.bindToElement(targetClass);
                                    }
                                }).execute();
                            }
                        }
                    });
                }
            } catch (Exception ignore) {
            }
        }
    }
}
