package cn.bugstack.guide.idea.plugin.module;


import cn.bugstack.guide.idea.plugin.infrastructure.DataSetting;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.project.DumbAwareAction;
import com.intellij.openapi.util.IconLoader;
import org.jetbrains.annotations.NotNull;

public class RefreshBar extends DumbAwareAction {

    private ViewBars panel;

    public RefreshBar(ViewBars panel) {
        super("刷新指数", "Click to refresh", IconLoader.getIcon("/icons/refresh.svg"));
        this.panel = panel;
    }

    @Override
    public void actionPerformed(@NotNull AnActionEvent e) {
        panel.getConsoleUI().addRows(DataSetting.getInstance().getGids());
    }

}
