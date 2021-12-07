package cn.bugstack.guide.idea.plugin.infrastructure.po;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author: 小傅哥，微信：fustack
 * @github: https://github.com/fuzhengwei
 * @Copyright: 公众号：bugstack虫洞栈 | 博客：https://bugstack.cn - 沉淀、分享、成长，让自己和他人都能有所收获！
 */
public class Dao extends Base {

    private Model model;

    public Dao(String comment, String name, Model model) {
        super(comment, name);
        this.model = model;
    }

    public Model getModel() {
        return model;
    }

    @Override
    public Set<String> getImports() {
        Set<String> imports = new HashSet<>();
        imports.add(model.getPackage() + "." + model.getSimpleName());
        List<Field> fields = model.getFields();
        for (Field field : fields) {
            if (field.isId() && field.isImport()) {
                imports.add(field.getTypeName());
                break;
            }
        }
        return imports;
    }

}
