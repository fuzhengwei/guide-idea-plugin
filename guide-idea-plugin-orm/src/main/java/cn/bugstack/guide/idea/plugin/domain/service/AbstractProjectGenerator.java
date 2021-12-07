package cn.bugstack.guide.idea.plugin.domain.service;

import cn.bugstack.guide.idea.plugin.domain.model.vo.CodeGenContextVO;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.util.io.FileUtil;
import com.intellij.openapi.util.text.StringUtil;
import com.intellij.openapi.vfs.LocalFileSystem;
import com.intellij.openapi.vfs.VirtualFile;
import freemarker.template.Template;
import freemarker.template.TemplateException;

import java.io.File;
import java.io.IOException;
import java.io.StringWriter;
import java.nio.charset.StandardCharsets;

/**
 * @author: 小傅哥，微信：fustack
 * @github: https://github.com/fuzhengwei
 * @Copyright: 公众号：bugstack虫洞栈 | 博客：https://bugstack.cn - 沉淀、分享、成长，让自己和他人都能有所收获！
 */
public abstract class AbstractProjectGenerator extends GeneratorConfig implements IProjectGenerator {

    @Override
    public void generation(Project project, CodeGenContextVO codeGenContext) {
        generateORM(project, codeGenContext);
    }

    protected abstract void generateORM(Project project, CodeGenContextVO codeGenContext);

    public void writeFile(Project project, String packageName, String name, String ftl, Object dataModel) {
        VirtualFile virtualFile = null;
        try {
            virtualFile = createPackageDir(packageName).createChildData(project, name);
            StringWriter stringWriter = new StringWriter();
            Template template = super.getTemplate(ftl);
            template.process(dataModel, stringWriter);
            virtualFile.setBinaryContent(stringWriter.toString().getBytes(StandardCharsets.UTF_8));
        } catch (IOException | TemplateException e) {
            e.printStackTrace();
        }
    }

    private static VirtualFile createPackageDir(String packageName) {
        String path = FileUtil.toSystemIndependentName(StringUtil.replace(packageName, ".", "/"));
        new File(path).mkdirs();
        return LocalFileSystem.getInstance().refreshAndFindFileByPath(path);
    }
}
