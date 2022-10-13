package cn.bugstack.guide.idea.plugin.domain.service.imp;

import cn.bugstack.guide.idea.plugin.domain.model.vo.ProjectConfigVO;
import cn.bugstack.guide.idea.plugin.domain.service.AbstractProjectGenerator;
import com.intellij.openapi.project.Project;

public class ProjectGeneratorImpl extends AbstractProjectGenerator {

    @Override
    protected void generateProjectPOM(Project project, String entryPath, ProjectConfigVO projectConfig) {
        writeFile(project, "/", entryPath, "pom.xml", "pom.ftl", projectConfig);
    }

    @Override
    protected void generateProjectDDD(Project project, String entryPath, ProjectConfigVO projectConfig) {
        // create application
        writeFile(project, "src/main/java/" + projectConfig.get_package() + ".application", entryPath, "package-info.java", "application/package-info.ftl", projectConfig);

        // create common
        writeFile(project, "src/main/java/" + projectConfig.get_package() + ".common", entryPath, "package-info.java", "common/package-info.ftl", projectConfig);

        // create domain
        writeFile(project, "src/main/java/" + projectConfig.get_package() + ".domain", entryPath, "package-info.java", "domain/package-info.ftl", projectConfig);

        // create infrastructure
        writeFile(project, "src/main/java/" + projectConfig.get_package() + ".infrastructure", entryPath, "package-info.java", "infrastructure/package-info.ftl", projectConfig);

        // create interfaces
        writeFile(project, "src/main/java/" + projectConfig.get_package() + ".interfaces", entryPath, "package-info.java", "interfaces/package-info.ftl", projectConfig);
    }

    @Override
    protected void generateApplication(Project project, String entryPath, ProjectConfigVO projectConfig) {
        writeFile(project, "src/main/java/" + projectConfig.get_package(), entryPath, projectConfig.get_artifactId() + "Application.java", "application.ftl", projectConfig);
    }

    @Override
    protected void generateYml(Project project, String entryPath, ProjectConfigVO projectConfig) {

        writeFile(project, "src/main/resources/", entryPath, "application.yml", "yml.ftl", projectConfig);
    }

    @Override
    protected void generateCommon(Project project, String entryPath, ProjectConfigVO projectConfig) {
        writeFile(project, "src/main/java/" + projectConfig.get_package() + "/common", entryPath, "Constants.java", "common/Constants.ftl", projectConfig);
    }

}
