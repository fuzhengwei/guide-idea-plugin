package cn.bugstack.guide.idea.plugin.action;

import cn.bugstack.guide.idea.plugin.domain.service.IProjectGenerator;
import cn.bugstack.guide.idea.plugin.domain.service.impl.ProjectGeneratorImpl;
import cn.bugstack.guide.idea.plugin.ui.ORMSettingsUI;
import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.actionSystem.CommonDataKeys;
import com.intellij.openapi.options.ShowSettingsUtil;
import com.intellij.openapi.project.Project;

/**
 * @author: 小傅哥，微信：fustack
 * @github: https://github.com/fuzhengwei
 * @Copyright: 公众号：bugstack虫洞栈 | 博客：https://bugstack.cn - 沉淀、分享、成长，让自己和他人都能有所收获！
 */
public class CodeGenerateAction extends AnAction {

    private IProjectGenerator projectGenerator = new ProjectGeneratorImpl();

    @Override
    public void actionPerformed(AnActionEvent e) {
        Project project = e.getRequiredData(CommonDataKeys.PROJECT);
        ShowSettingsUtil.getInstance().editConfigurable(project, new ORMSettingsUI(project, projectGenerator));
    }

}
