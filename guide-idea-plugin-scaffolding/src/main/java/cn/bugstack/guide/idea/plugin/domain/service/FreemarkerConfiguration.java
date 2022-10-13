package cn.bugstack.guide.idea.plugin.domain.service;

import freemarker.template.Configuration;
import freemarker.template.Template;

import java.io.IOException;

public class FreemarkerConfiguration extends Configuration {

    public FreemarkerConfiguration() {
        this("/template");
    }

    private FreemarkerConfiguration(String basePackagePath) {
        super(Configuration.DEFAULT_INCOMPATIBLE_IMPROVEMENTS);
        setDefaultEncoding("UTF-8");
        setClassForTemplateLoading(getClass(), basePackagePath);
    }

    public Template getTemplate(String ftl) throws IOException {
        return this.getTemplate(ftl, "UTF-8");
    }

}
