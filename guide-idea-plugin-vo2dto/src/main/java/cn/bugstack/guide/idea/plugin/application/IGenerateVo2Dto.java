package cn.bugstack.guide.idea.plugin.application;

import com.intellij.openapi.actionSystem.DataContext;
import com.intellij.openapi.project.Project;

public interface IGenerateVo2Dto {

    void doGenerate(Project project, DataContext dataContext);

}
