package cn.bugstack.guide.idea.plugin.module;

import cn.bugstack.guide.idea.plugin.domain.service.IProjectGenerator;
import cn.bugstack.guide.idea.plugin.domain.service.imp.ProjectGeneratorImpl;
import cn.bugstack.guide.idea.plugin.infrastructure.DataSetting;
import cn.bugstack.guide.idea.plugin.infrastructure.ICONS;
import cn.bugstack.guide.idea.plugin.infrastructure.MsgBundle;
import cn.bugstack.guide.idea.plugin.ui.ProjectConfigUI;
import com.intellij.ide.util.projectWizard.*;
import com.intellij.openapi.application.ApplicationManager;
import com.intellij.openapi.application.Result;
import com.intellij.openapi.command.WriteCommandAction;
import com.intellij.openapi.module.ModuleType;
import com.intellij.openapi.module.StdModuleTypes;
import com.intellij.openapi.options.ConfigurationException;
import com.intellij.openapi.project.DumbService;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.roots.ModifiableRootModel;
import com.intellij.openapi.roots.ui.configuration.ModulesProvider;
import com.intellij.openapi.startup.StartupManager;
import com.intellij.openapi.util.io.FileUtil;
import com.intellij.openapi.util.text.StringUtil;
import com.intellij.openapi.vfs.LocalFileSystem;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.util.DisposeAwareRunnable;
import org.jetbrains.annotations.Nls;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;
import java.io.File;
import java.util.Objects;

public class DDDModuleBuilder extends ModuleBuilder {

    private IProjectGenerator projectGenerator = new ProjectGeneratorImpl();

    @Override
    public Icon getNodeIcon() {
        return ICONS.SPRING_BOOT;
    }

    @Override
    public ModuleType<?> getModuleType() {
        return StdModuleTypes.JAVA;
    }

    @Override
    public @Nls(capitalization = Nls.Capitalization.Title)
    String getPresentableName() {
        return "Spring Boot";
    }

    @Override
    public String getDescription() {
        return MsgBundle.message("wizard.spring.boot.description");
    }

    /**
     * 重写 builderId 挂载自定义模板
     */
    @Nullable
    @Override
    public String getBuilderId() {
        return getClass().getName();
    }

    @Override
    public @Nullable ModuleWizardStep modifySettingsStep(@NotNull SettingsStep settingsStep) {
        ModuleNameLocationSettings moduleNameLocationSettings = settingsStep.getModuleNameLocationSettings();
        String artifactId = DataSetting.getInstance().getProjectConfig().get_artifactId();
        if (null != moduleNameLocationSettings && !StringUtil.isEmptyOrSpaces(artifactId)) {
            moduleNameLocationSettings.setModuleName(artifactId);
        }
        return super.modifySettingsStep(settingsStep);
    }

    @Override
    public void setupRootModel(@NotNull ModifiableRootModel rootModel) throws ConfigurationException {

        // 设置 JDK
        if (null != this.myJdk) {
            rootModel.setSdk(this.myJdk);
        } else {
            rootModel.inheritSdk();
        }

        // 生成工程路径
        String path = FileUtil.toSystemIndependentName(Objects.requireNonNull(getContentEntryPath()));
        new File(path).mkdirs();
        VirtualFile virtualFile = LocalFileSystem.getInstance().refreshAndFindFileByPath(path);
        rootModel.addContentEntry(virtualFile);

        Project project = rootModel.getProject();

        // 创建工程结构
        Runnable r = () -> new WriteCommandAction<VirtualFile>(project) {
            @Override
            protected void run(@NotNull Result<VirtualFile> result) throws Throwable {
                projectGenerator.doGenerator(project, getContentEntryPath(), DataSetting.getInstance().getProjectConfig());
            }
        }.execute();

        if (ApplicationManager.getApplication().isUnitTestMode()
                || ApplicationManager.getApplication().isHeadlessEnvironment()) {
            r.run();
            return;
        }

        if (!project.isInitialized()) {
            StartupManager.getInstance(project).registerPostStartupActivity(DisposeAwareRunnable.create(r, project));
            return;
        }

        if (DumbService.isDumbAware(r)) {
            r.run();
        } else {
            DumbService.getInstance(project).runWhenSmart(DisposeAwareRunnable.create(r, project));
        }

    }

    @Override
    public ModuleWizardStep[] createWizardSteps(@NotNull WizardContext wizardContext, @NotNull ModulesProvider modulesProvider) {

        // 添加工程配置步骤，可以自己定义需要的步骤，如果有多个可以依次添加
        DDDModuleConfigStep moduleConfigStep = new DDDModuleConfigStep(new ProjectConfigUI());

        return new ModuleWizardStep[]{moduleConfigStep};
    }
}
