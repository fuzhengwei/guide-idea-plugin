package cn.bugstack.guide.idea.plugin.infrastructure.po;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author: 小傅哥，微信：fustack
 * @github: https://github.com/fuzhengwei
 * @Copyright: 公众号：bugstack虫洞栈 | 博客：https://bugstack.cn - 沉淀、分享、成长，让自己和他人都能有所收获！
 */
public class Model extends Base{

    private String tableName;
    private List<Field> fields;

    public Model(String comment, String name, String tableName, List<Field> fields) {
        super(comment, name);
        this.tableName = tableName;
        this.fields = fields;
    }

    public List<Field> getFields() {
        return fields;
    }

    public String getTableName() {
        return tableName;
    }

    @Override
    public Set<String> getImports() {
        Set<String> imports = new HashSet<>();
        List<Field> fields = getFields();
        for (Field field : fields) {
            if (field.isImport()) {
                imports.add(field.getTypeName());
            }
        }
        return imports;
    }

}
