package cn.bugstack.guide.idea.plugin.action;

import cn.bugstack.guide.idea.plugin.application.IGenerateVo2Dto;
import cn.bugstack.guide.idea.plugin.domain.service.impl.GenerateVo2DtoImpl;
import com.intellij.openapi.actionSystem.*;
import com.intellij.openapi.application.Application;
import com.intellij.openapi.application.ApplicationManager;
import com.intellij.openapi.application.Result;
import com.intellij.openapi.command.WriteCommandAction;
import com.intellij.openapi.editor.CaretModel;
import com.intellij.openapi.editor.Document;
import com.intellij.openapi.editor.Editor;
import com.intellij.openapi.editor.ScrollType;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.ui.Messages;
import com.intellij.openapi.ui.TestInputDialog;
import com.intellij.psi.JavaPsiFacade;
import com.intellij.psi.PsiClass;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiMethod;
import com.intellij.psi.search.GlobalSearchScope;
import com.intellij.psi.search.PsiShortNamesCache;
import org.jetbrains.annotations.NotNull;

import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;
import java.util.regex.Pattern;

public class Vo2DtoGenerateAction extends AnAction {

    private IGenerateVo2Dto generateVo2Dto = new GenerateVo2DtoImpl();

    @Override
    public void actionPerformed(AnActionEvent event) {
        try {
            // 织入代码
            generateVo2Dto.doGenerate(event.getProject(), event.getDataContext());
        } catch (Exception e) {
            Messages.showErrorDialog(event.getProject(), "错误提示", "请按规复制对象后，光标放到需要织入的对象上！");
        }
    }

}
