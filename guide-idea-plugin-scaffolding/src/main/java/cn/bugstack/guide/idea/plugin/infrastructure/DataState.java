package cn.bugstack.guide.idea.plugin.infrastructure;

import cn.bugstack.guide.idea.plugin.domain.model.vo.ProjectConfigVO;

public class DataState {

   private ProjectConfigVO projectConfigVO = new ProjectConfigVO();

    public ProjectConfigVO getProjectConfigVO() {
        return projectConfigVO;
    }

    public void setProjectConfigVO(ProjectConfigVO projectConfigVO) {
        this.projectConfigVO = projectConfigVO;
    }

}
