package cn.bugstack.guide.idea.plugin.rule;

import com.intellij.codeInsight.daemon.impl.quickfix.DeleteElementFix;
import com.intellij.codeInspection.AbstractBaseJavaLocalInspectionTool;
import com.intellij.codeInspection.ProblemHighlightType;
import com.intellij.codeInspection.ProblemsHolder;
import com.intellij.openapi.editor.Editor;
import com.intellij.openapi.project.Project;
import com.intellij.psi.*;
import com.siyeh.ig.psiutils.MethodCallUtils;
import org.jetbrains.annotations.Nls;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class FastJsonRule extends AbstractBaseJavaLocalInspectionTool {

    @Override
    @NotNull
    public PsiElementVisitor buildVisitor(@NotNull ProblemsHolder holder, boolean isOnTheFly) {
        return new JavaElementVisitor() {
            @Override
            public void visitMethodCallExpression(PsiMethodCallExpression expression) {
                if (hasFullQualifiedName(expression, "com.alibaba.fastjson.parser.ParserConfig", "setAutoTypeSupport")) {
                    PsiExpression[] args = expression.getArgumentList().getExpressions();
                    if (args.length == 1 &&
                            args[0] instanceof PsiLiteralExpression &&
                            Boolean.TRUE.equals(((PsiLiteralExpression) args[0]).getValue())
                    ) {
                        holder.registerProblem(
                                expression,
                                "FastJson unserialization risk",
                                ProblemHighlightType.GENERIC_ERROR_OR_WARNING,
                                new DeleteElementQuickFix(expression, "!Fix: remove setAutoTypeSupport")
                        );
                    }
                }
            }
        };
    }

    public static class DeleteElementQuickFix extends DeleteElementFix {

        public DeleteElementQuickFix(@NotNull PsiElement element, @NotNull @Nls String text) {
            super(element, text);
        }

        @Override
        public void invoke(@NotNull Project project, @NotNull PsiFile file, @Nullable Editor editor, @NotNull PsiElement startElement, @NotNull PsiElement endElement) {
            super.invoke(project, file, editor, startElement, endElement);
        }
    }

    public static boolean hasFullQualifiedName(PsiMethodCallExpression methodCall, String qualifiedName, String methodName) {
        String methodCallName = MethodCallUtils.getMethodName(methodCall);
        if (!methodName.equals(methodCallName)) {
            return false;
        }

        PsiMethod method = methodCall.resolveMethod();
        if (method == null) {
            return false;
        }

        PsiClass containingClass = method.getContainingClass();
        if (containingClass == null) {
            return false;
        }

        return qualifiedName.equals(containingClass.getQualifiedName());
    }


}
