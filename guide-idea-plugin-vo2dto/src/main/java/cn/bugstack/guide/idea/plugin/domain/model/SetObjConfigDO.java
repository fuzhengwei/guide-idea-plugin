package cn.bugstack.guide.idea.plugin.domain.model;

import java.util.List;
import java.util.Map;

public class SetObjConfigDO {

    /** 类属性名 */
    private String clazzParamName;
    /** param 集合，保证顺序性 */
    private List<String> paramList;
    /** key：param val：set方法 */
    private Map<String, String> paramMtdMap;

    public SetObjConfigDO(String clazzParamName, List<String> paramList, Map<String, String> paramMtdMap) {
        this.clazzParamName = clazzParamName;
        this.paramList = paramList;
        this.paramMtdMap = paramMtdMap;
    }

    public String getClazzParamName() {
        return clazzParamName;
    }

    public void setClazzParamName(String clazzParamName) {
        this.clazzParamName = clazzParamName;
    }

    public List<String> getParamList() {
        return paramList;
    }

    public void setParamList(List<String> paramList) {
        this.paramList = paramList;
    }

    public Map<String, String> getParamMtdMap() {
        return paramMtdMap;
    }

    public void setParamMtdMap(Map<String, String> paramMtdMap) {
        this.paramMtdMap = paramMtdMap;
    }
}
