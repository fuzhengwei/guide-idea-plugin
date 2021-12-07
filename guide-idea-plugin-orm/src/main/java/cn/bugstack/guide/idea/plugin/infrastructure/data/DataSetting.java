package cn.bugstack.guide.idea.plugin.infrastructure.data;

import cn.bugstack.guide.idea.plugin.domain.model.vo.ORMConfigVO;
import com.intellij.openapi.components.PersistentStateComponent;
import com.intellij.openapi.components.ServiceManager;
import com.intellij.openapi.components.State;
import com.intellij.openapi.components.Storage;
import com.intellij.openapi.project.Project;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * @author: 小傅哥，微信：fustack
 * @github: https://github.com/fuzhengwei
 * @Copyright: 公众号：bugstack虫洞栈 | 博客：https://bugstack.cn - 沉淀、分享、成长，让自己和他人都能有所收获！
 */
@State(name = "DataSetting", storages = @Storage("plugin.xml"))
public class DataSetting implements PersistentStateComponent<DataState> {

    private DataState state = new DataState();

    public static DataSetting getInstance(Project project) {
        return ServiceManager.getService(project, DataSetting.class);
    }

    @Nullable
    @Override
    public DataState getState() {
        return this.state;
    }

    @Override
    public void loadState(@NotNull DataState state) {
        this.state = state;
    }

    public ORMConfigVO getORMConfig() {
        return state.getOrmConfigVO();
    }

}
