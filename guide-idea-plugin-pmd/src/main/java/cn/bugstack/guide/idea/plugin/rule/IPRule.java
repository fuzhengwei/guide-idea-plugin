package cn.bugstack.guide.idea.plugin.rule;

import com.intellij.codeInspection.AbstractBaseJavaLocalInspectionTool;
import com.intellij.codeInspection.ProblemHighlightType;
import com.intellij.codeInspection.ProblemsHolder;
import com.intellij.psi.JavaElementVisitor;
import com.intellij.psi.JavaTokenType;
import com.intellij.psi.PsiElementVisitor;
import com.intellij.psi.PsiLiteralExpression;
import com.intellij.psi.tree.IElementType;
import org.jetbrains.annotations.NotNull;

public class IPRule extends AbstractBaseJavaLocalInspectionTool {

    @Override
    public @NotNull PsiElementVisitor buildVisitor(@NotNull ProblemsHolder holder, boolean isOnTheFly) {

        return new JavaElementVisitor() {
            @Override
            public void visitLiteralExpression(PsiLiteralExpression expression) {
                IElementType type = expression.getFirstChild().getNode().getElementType();
                if (type == JavaTokenType.STRING_LITERAL) {
                    Object v = expression.getValue();
                    if (v != null && isSensitiveIp(v.toString())) {
                        holder.registerProblem(
                                expression,
                                "Hardcoded IP Address",
                                ProblemHighlightType.GENERIC_ERROR_OR_WARNING);
                    }
                }
            }
        };
    }

    private static boolean isSensitiveIp(String ip) {

        if (ip.length() < 7 || ip.length() > 15) {
            return false;
        }

        String[] ipArray = ip.split("\\.");
        if (ipArray.length != 4) {
            return false;
        }

        for (int i = 0; i < ipArray.length; i++) {
            try {
                int number = Integer.parseInt(ipArray[i]);
                // 判断每段数字是否都在0-255之间
                if (number < 0 || number > 255) {
                    return false;
                }

                // 忽略 127.0.0.1/8
                if (i == 0 && number == 127) {
                    return false;
                }
            } catch (Exception e) {
                return false;
            }
        }

        return !"255.255.255.255".equals(ip) &&
                !"0.0.0.0".equals(ip) &&
                !ip.startsWith("2.5.");
    }

}
