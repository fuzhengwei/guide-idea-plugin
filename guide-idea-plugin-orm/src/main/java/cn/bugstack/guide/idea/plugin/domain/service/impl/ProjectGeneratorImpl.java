package cn.bugstack.guide.idea.plugin.domain.service.impl;

import cn.bugstack.guide.idea.plugin.domain.model.vo.CodeGenContextVO;
import cn.bugstack.guide.idea.plugin.domain.service.AbstractProjectGenerator;
import cn.bugstack.guide.idea.plugin.infrastructure.po.*;
import cn.bugstack.guide.idea.plugin.infrastructure.utils.JavaType;
import com.google.common.base.CaseFormat;
import com.intellij.openapi.project.Project;

import java.util.ArrayList;
import java.util.List;

/**
 * @author: 小傅哥，微信：fustack
 * @github: https://github.com/fuzhengwei
 * @Copyright: 公众号：bugstack虫洞栈 | 博客：https://bugstack.cn - 沉淀、分享、成长，让自己和他人都能有所收获！
 */
public class ProjectGeneratorImpl extends AbstractProjectGenerator {

    @Override
    protected void generateORM(Project project, CodeGenContextVO codeGenContext) {

        List<Table> tables = codeGenContext.getTables();
        for (Table table : tables) {
            List<Column> columns = table.getColumns();
            List<Field> fields = new ArrayList<>();

            for (Column column : columns) {
                Field field = new Field(column.getComment(), JavaType.convertType(column.getType()), column.getName());
                field.setId(column.isId());
                fields.add(field);
            }

            // 生成PO
            Model model = new Model(table.getComment(), codeGenContext.getModelPackage() + CaseFormat.UPPER_UNDERSCORE.to(CaseFormat.UPPER_CAMEL, table.getName()), table.getName(), fields);
            writeFile(project, codeGenContext.getModelPackage(), model.getSimpleName() + ".java", "domain/orm/model.ftl", model);

            // 生成DAO
            Dao dao = new Dao(table.getComment(), codeGenContext.getDaoPackage() + "I" + CaseFormat.UPPER_UNDERSCORE.to(CaseFormat.UPPER_CAMEL, table.getName()) + "Dao", model);
            writeFile(project, codeGenContext.getDaoPackage(), dao.getSimpleName() + ".java", "domain/orm/dao.ftl", dao);

            // 生成Mapper
            writeFile(project, codeGenContext.getMapperDir(), dao.getModel().getSimpleName() + "Mapper.xml", "domain/orm/mapper.ftl", dao);
        }

    }

}
