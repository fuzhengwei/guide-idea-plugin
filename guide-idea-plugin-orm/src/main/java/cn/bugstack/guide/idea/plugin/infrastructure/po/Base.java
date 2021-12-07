package cn.bugstack.guide.idea.plugin.infrastructure.po;

import com.google.common.base.CaseFormat;
import org.apache.commons.lang3.StringUtils;

import java.util.Set;

/**
 * @author: 小傅哥，微信：fustack
 * @github: https://github.com/fuzhengwei
 * @Copyright: 公众号：bugstack虫洞栈 | 博客：https://bugstack.cn - 沉淀、分享、成长，让自己和他人都能有所收获！
 */
public abstract class Base {

    private int ormType;
    private String comment;
    private String name;

    public Base(String comment, String name) {
        this.comment = comment;
        this.name = name;
    }

    public String getPackage() {
        String str = StringUtils.substringAfterLast(name, "java/");
        str = str.substring(0, str.lastIndexOf(getSimpleName()) - 1);
        return str.replaceAll("/",".");
    }

    public abstract Set<String> getImports();

    public String getComment() {
        return comment;
    }

    public String getSimpleName() {
        return name.lastIndexOf("/") == -1 ? name : StringUtils.substringAfterLast(name, "/");
    }

    public String getVarName() {
        return CaseFormat.UPPER_CAMEL.to(CaseFormat.LOWER_CAMEL, getSimpleName());
    }

    public String getName() {
        return name;
    }

    public void setOrmType(int ormType) {
        this.ormType = ormType;
    }

    public int getOrmType() {
        return ormType;
    }

}
