package ${package};

import org.apache.ibatis.annotations.Mapper;
<#list imports as import>
import ${import};
</#list>

/**
 * ${comment}
 */
@Mapper
public interface ${simpleName} {

<#list model.fields as field>
    <#if field.id>
    /**
     * 通过ID查询单个${model.comment}
     *
     * @param id ID
     * @return {@link ${model.simpleName}}
     */
     ${model.simpleName} findById(${field.typeSimpleName} id);

    /**
     * 新增${model.comment}
     *
     * @param ${model.varName} ${model.comment}
     */
    void insert(${model.simpleName} ${model.varName});

    /**
     * 修改${model.comment}
     *
     * @param ${model.varName} ${model.comment}
     */
    void update(${model.simpleName} ${model.varName});

    /**
     * 通过ID删除单个${model.comment}
     *
     * @param id ID
     */
    void deleteById(${field.typeSimpleName} id);
    </#if>
</#list>

}