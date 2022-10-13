package cn.bugstack.guide.idea.plugin.module;

import cn.bugstack.guide.idea.plugin.ui.ConsoleUI;
import com.intellij.openapi.actionSystem.ActionManager;
import com.intellij.openapi.actionSystem.ActionToolbar;
import com.intellij.openapi.actionSystem.DefaultActionGroup;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.ui.SimpleToolWindowPanel;
import com.intellij.ui.JBSplitter;

/**
 * @description: 窗体面板
 * @author: 小傅哥 | 微信：fustack 公众号：bugstack虫洞栈
 * @github: https://github.com/fuzhengwei
 */
public class ViewBars extends SimpleToolWindowPanel {

    private Project project;
    private ConsoleUI consoleUI;

    public ViewBars(Project project) {
        super(false, true);
        this.project = project;
        consoleUI = new ConsoleUI();

        // 设置窗体侧边栏按钮
        DefaultActionGroup group = new DefaultActionGroup();
        group.add(new SettingBar(this));
        group.add(new RefreshBar(this));

        ActionToolbar toolbar = ActionManager.getInstance().createActionToolbar("bar", group, false);
        toolbar.setTargetComponent(this);
        setToolbar(toolbar.getComponent());

        // 添加
        JBSplitter splitter = new JBSplitter(false);
        splitter.setSplitterProportionKey("main.splitter.key");
        splitter.setFirstComponent(consoleUI.getPanel());
        splitter.setProportion(0.3f);
        setContent(splitter);
    }

    public Project getProject() {
        return project;
    }

    public ConsoleUI getConsoleUI() {
        return consoleUI;
    }
}
