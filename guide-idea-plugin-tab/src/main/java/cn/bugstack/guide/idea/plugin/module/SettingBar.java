package cn.bugstack.guide.idea.plugin.module;

import cn.bugstack.guide.idea.plugin.ui.GidConfig;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.options.ShowSettingsUtil;
import com.intellij.openapi.project.DumbAwareAction;
import com.intellij.openapi.util.IconLoader;
import org.jetbrains.annotations.NotNull;

public class SettingBar extends DumbAwareAction {

    private ViewBars panel;

    public SettingBar(ViewBars panel) {
        super("配置股票", "Click to setting", IconLoader.getIcon("/icons/config.svg"));
        this.panel = panel;
    }

    @Override
    public void actionPerformed(@NotNull AnActionEvent e) {
        ShowSettingsUtil.getInstance().editConfigurable(panel.getProject(), new GidConfig(panel.getConsoleUI()));
    }

}
