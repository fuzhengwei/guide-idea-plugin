package cn.bugstack.guide.idea.plugin.infrastructure.data;

import cn.bugstack.guide.idea.plugin.domain.model.vo.ORMConfigVO;

/**
 * @author: 小傅哥，微信：fustack
 * @github: https://github.com/fuzhengwei
 * @Copyright: 公众号：bugstack虫洞栈 | 博客：https://bugstack.cn - 沉淀、分享、成长，让自己和他人都能有所收获！
 */
public class DataState {

    private ORMConfigVO ormConfigVO = new ORMConfigVO();

    public ORMConfigVO getOrmConfigVO() {
        return ormConfigVO;
    }

    public void setOrmConfigVO(ORMConfigVO ormConfigVO) {
        this.ormConfigVO = ormConfigVO;
    }

}
