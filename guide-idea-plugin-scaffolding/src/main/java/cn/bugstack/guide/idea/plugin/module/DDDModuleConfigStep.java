package cn.bugstack.guide.idea.plugin.module;

import cn.bugstack.guide.idea.plugin.domain.model.vo.ProjectConfigVO;
import cn.bugstack.guide.idea.plugin.infrastructure.DataSetting;
import cn.bugstack.guide.idea.plugin.ui.ProjectConfigUI;
import com.intellij.ide.util.projectWizard.ModuleWizardStep;
import com.intellij.openapi.options.ConfigurationException;

import javax.swing.*;

public class DDDModuleConfigStep extends ModuleWizardStep {

    private ProjectConfigUI projectConfigUI;

    public DDDModuleConfigStep(ProjectConfigUI projectConfigUI) {
        this.projectConfigUI = projectConfigUI;
    }

    @Override
    public JComponent getComponent() {
        return projectConfigUI.getComponent();
    }

    @Override
    public void updateDataModel() {

    }

    @Override
    public boolean validate() throws ConfigurationException {
        // 获取配置信息，写入到 DataSetting
        ProjectConfigVO projectConfig = DataSetting.getInstance().getProjectConfig();
        projectConfig.set_groupId(projectConfigUI.getGroupIdField().getText());
        projectConfig.set_artifactId(projectConfigUI.getArtifactIdField().getText());
        projectConfig.set_version(projectConfigUI.getVersionField().getText());
        projectConfig.set_package(projectConfigUI.getPackageField().getText());

        return super.validate();
    }

}
