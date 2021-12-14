package cn.bugstack.guide.idea.plugin.domain.service;

import cn.bugstack.guide.idea.plugin.application.IGenerateVo2Dto;
import cn.bugstack.guide.idea.plugin.domain.model.GenerateContext;
import cn.bugstack.guide.idea.plugin.domain.model.GetObjConfigDO;
import cn.bugstack.guide.idea.plugin.domain.model.SetObjConfigDO;
import com.intellij.openapi.actionSystem.DataContext;
import com.intellij.openapi.project.Project;

public abstract class AbstractGenerateVo2Dto implements IGenerateVo2Dto {

    @Override
    public void doGenerate(Project project, DataContext dataContext) {
        // 1. 获取上下文
        GenerateContext generateContext = this.getGenerateContext(project, dataContext);

        // 2. 获取对象的 set 方法集合
        SetObjConfigDO setObjConfigDO = this.getSetObjConfigDO(generateContext);

        // 3. 获取对的的 get 方法集合 【从剪切板获取】
        GetObjConfigDO getObjConfigDO = this.getObjConfigDOByClipboardText(generateContext);

        // 4. 织入代码 set->get
        this.weavingSetGetCode(generateContext, setObjConfigDO, getObjConfigDO);
    }

    protected abstract GenerateContext getGenerateContext(Project project, DataContext dataContext);

    protected abstract SetObjConfigDO getSetObjConfigDO(GenerateContext generateContext);

    protected abstract GetObjConfigDO getObjConfigDOByClipboardText(GenerateContext generateContext);

    protected abstract void weavingSetGetCode(GenerateContext generateContext, SetObjConfigDO setObjConfigDO, GetObjConfigDO getObjConfigDO);

}
