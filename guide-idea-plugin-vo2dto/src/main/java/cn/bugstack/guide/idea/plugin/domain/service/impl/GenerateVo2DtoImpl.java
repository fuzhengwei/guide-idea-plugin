package cn.bugstack.guide.idea.plugin.domain.service.impl;

import cn.bugstack.guide.idea.plugin.domain.model.GenerateContext;
import cn.bugstack.guide.idea.plugin.domain.model.GetObjConfigDO;
import cn.bugstack.guide.idea.plugin.domain.model.SetObjConfigDO;
import cn.bugstack.guide.idea.plugin.domain.service.AbstractGenerateVo2Dto;
import cn.bugstack.guide.idea.plugin.infrastructure.Utils;
import com.intellij.openapi.actionSystem.CommonDataKeys;
import com.intellij.openapi.actionSystem.DataContext;
import com.intellij.openapi.application.Application;
import com.intellij.openapi.application.ApplicationManager;
import com.intellij.openapi.application.Result;
import com.intellij.openapi.command.WriteCommandAction;
import com.intellij.openapi.editor.Document;
import com.intellij.openapi.editor.Editor;
import com.intellij.openapi.editor.ScrollType;
import com.intellij.openapi.project.Project;
import com.intellij.psi.PsiClass;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiMethod;
import com.intellij.psi.search.GlobalSearchScope;
import com.intellij.psi.search.PsiShortNamesCache;
import org.jetbrains.annotations.NotNull;

import java.util.*;
import java.util.regex.Pattern;

public class GenerateVo2DtoImpl extends AbstractGenerateVo2Dto {

    private final String setRegex = "set(\\w+)";
    private final String getRegex = "get(\\w+)";

    @Override
    protected GenerateContext getGenerateContext(Project project, DataContext dataContext) {

        // 基础信息
        Editor editor = CommonDataKeys.EDITOR.getData(dataContext);
        PsiElement psiElement = CommonDataKeys.PSI_ELEMENT.getData(dataContext);
        assert editor != null;
        Document document = editor.getDocument();

        // 封装生成对象上下文
        GenerateContext generateContext = new GenerateContext();
        generateContext.setProject(project);
        generateContext.setDataContext(dataContext);
        generateContext.setEditor(editor);
        generateContext.setPsiElement(psiElement);
        generateContext.setOffset(editor.getCaretModel().getOffset());
        generateContext.setDocument(document);
        generateContext.setLineNumber(document.getLineNumber(generateContext.getOffset()));
        generateContext.setStartOffset(document.getLineStartOffset(generateContext.getLineNumber()));
        generateContext.setEditorText(document.getCharsSequence());

        return generateContext;
    }

    @Override
    protected SetObjConfigDO getSetObjConfigDO(GenerateContext generateContext) {

        PsiClass psiClass = (PsiClass) generateContext.getPsiElement();
        String name = psiClass.getName();
        String firstChar = Objects.requireNonNull(psiClass.getName()).substring(0, 1).toLowerCase();
        assert name != null;
        String clazzParamName = firstChar + name.substring(1);

        Pattern setMtd = Pattern.compile(setRegex);

        // 获取类的set方法并存放起来
        List<String> paramList = new ArrayList<>();
        Map<String, String> paramMtdMap = new HashMap<>();
        PsiMethod[] methods = psiClass.getMethods();
        for (PsiMethod method : methods) {
            String methodName = method.getName();
            if (Pattern.matches(setRegex, methodName)) {
                // 替换属性
                String param = setMtd.matcher(methodName).replaceAll("$1").toLowerCase();

                // 保存获取的属性信息
                paramMtdMap.put(param, methodName);
                paramList.add(param);
            }
        }

        return new SetObjConfigDO(clazzParamName, paramList, paramMtdMap);
    }

    @Override
    protected GetObjConfigDO getObjConfigDOByClipboardText(GenerateContext generateContext) {
        // 获取剪切板信息 【实际使用可补充一些必要的参数判断】
        String systemClipboardText = Utils.getSystemClipboardText().trim();

        // 按照默认规则提取信息，例如：UserDto userDto
        String[] split = systemClipboardText.split("\\s");

        assert 2 == split.length;

        String clazzName = split[0].trim();
        String clazzParam = split[1].trim();

        // 获取类
        PsiClass[] psiClasses = PsiShortNamesCache.getInstance(generateContext.getProject()).getClassesByName(clazzName, GlobalSearchScope.projectScope(generateContext.getProject()));

        Map<String, String> paramMtdMap = new HashMap<>();
        PsiMethod[] methodsDTO = psiClasses[0].getMethods();
        Pattern getM = Pattern.compile(getRegex);

        for (PsiMethod method : methodsDTO) {
            String methodName = method.getName();
            if (Pattern.matches(getRegex, methodName)) {
                String param = getM.matcher(methodName).replaceAll("$1").toLowerCase();
                paramMtdMap.put(param, methodName);
            }
        }

        return new GetObjConfigDO(clazzName, clazzParam, paramMtdMap);
    }

    @Override
    protected void weavingSetGetCode(GenerateContext generateContext, SetObjConfigDO setObjConfigDO, GetObjConfigDO getObjConfigDO) {
        Application application = ApplicationManager.getApplication();

        // 获取空格位置长度
        int distance = Utils.getWordStartOffset(generateContext.getEditorText(), generateContext.getOffset()) - generateContext.getStartOffset();

        application.runWriteAction(() -> {
            StringBuilder blankSpace = new StringBuilder();
            for (int i = 0; i < distance; i++) {
                blankSpace.append(" ");
            }

            int lineNumberCurrent = generateContext.getDocument().getLineNumber(generateContext.getOffset()) + 1;

            List<String> setMtdList = setObjConfigDO.getParamList();
            for (String param : setMtdList) {
                int lineStartOffset = generateContext.getDocument().getLineStartOffset(lineNumberCurrent++);

                new WriteCommandAction(generateContext.getProject()) {

                    @Override
                    protected void run(@NotNull Result result) throws Throwable {
                        generateContext.getDocument().insertString(lineStartOffset, blankSpace + setObjConfigDO.getClazzParamName() + "." + setObjConfigDO.getParamMtdMap().get(param) + "(" + (null == getObjConfigDO.getParamMtdMap().get(param) ? "" : getObjConfigDO.getClazzParam() + "." + getObjConfigDO.getParamMtdMap().get(param) + "()") + ");\n");
                        generateContext.getEditor().getCaretModel().moveToOffset(lineStartOffset + 2);
                        generateContext.getEditor().getScrollingModel().scrollToCaret(ScrollType.MAKE_VISIBLE);
                    }
                }.execute();
            }

        });

    }

}
