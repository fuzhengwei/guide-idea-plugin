package cn.bugstack.guide.idea.plugin.factory;

import cn.bugstack.guide.idea.plugin.module.ViewBars;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.wm.ToolWindow;
import com.intellij.openapi.wm.ToolWindowFactory;
import com.intellij.ui.content.Content;
import com.intellij.ui.content.ContentFactory;
import org.jetbrains.annotations.NotNull;

public class TabFactory implements ToolWindowFactory {

    @Override
    public void createToolWindowContent(@NotNull Project project, @NotNull ToolWindow toolWindow) {
        // 窗体
        ViewBars viewPanel = new ViewBars(project);
        // 获取内容工厂的实例
        ContentFactory contentFactory = ContentFactory.SERVICE.getInstance();
        // 获取 ToolWindow 显示的内容
        Content content = contentFactory.createContent(viewPanel, "股票", false);
        // 设置 ToolWindow 显示的内容
        toolWindow.getContentManager().addContent(content, 0);

        // 定时任务，自动刷新股票
        /* 因每日查询次数限制，这里就不开定时任务了，用户可以自行申请 https://dashboard.juhe.cn/home
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                viewPanel.getConsoleUI().addRows(DataSetting.getInstance().getGids());
            }
        }, 3000, 2000);*/
    }

}
