package cn.bugstack.guide.idea.plugin.domain.service;

import freemarker.template.Configuration;
import freemarker.template.Template;

import java.io.IOException;

/**
 * @author: 小傅哥，微信：fustack
 * @github: https://github.com/fuzhengwei
 * @Copyright: 公众号：bugstack虫洞栈 | 博客：https://bugstack.cn - 沉淀、分享、成长，让自己和他人都能有所收获！
 */
public class GeneratorConfig {

    private static String ENCODING = "UTF-8";

    private static FreemarkerConfiguration freemarker = new FreemarkerConfiguration("/template");

    protected Template getTemplate(String ftl) throws IOException {
        return freemarker.getTemplate(ftl, ENCODING);
    }

    static class FreemarkerConfiguration extends Configuration{

        public FreemarkerConfiguration(String basePackagePath) {
            super(Configuration.DEFAULT_INCOMPATIBLE_IMPROVEMENTS);
            setDefaultEncoding(ENCODING);
            setClassForTemplateLoading(getClass(), basePackagePath);
        }

    }

}
