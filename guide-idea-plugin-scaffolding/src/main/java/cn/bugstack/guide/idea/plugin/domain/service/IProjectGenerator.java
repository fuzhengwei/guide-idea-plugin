package cn.bugstack.guide.idea.plugin.domain.service;

import cn.bugstack.guide.idea.plugin.domain.model.vo.ProjectConfigVO;
import com.intellij.openapi.project.Project;

public interface IProjectGenerator {

    void doGenerator(Project project, String entryPath, ProjectConfigVO projectConfig);

}
