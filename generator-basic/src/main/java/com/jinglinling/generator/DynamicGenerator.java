package com.jinglinling.generator;

import com.jinglinling.model.MainTemplateConfig;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

import java.io.*;

/**
 * @Auther 谢骏晖
 * @Date 2024/12/5
 * @Description 动态文件生成
 */
public class DynamicGenerator {
    /*public static void main(String[] args) throws IOException, TemplateException {
        String projectPath = System.getProperty("user.dir");
        String inputPath = projectPath + File.separator + "src/main/resources/templates/MainTemplate.java.ftl";
        String outputPath = projectPath + File.separator + "MainTemplate.java";
        MainTemplateConfig mainTemplateConfig = new MainTemplateConfig();
        mainTemplateConfig.setAuthor("jinglinling");
        mainTemplateConfig.setLoop(false);
        mainTemplateConfig.setOutputText("求和结果： ");
        doGenerator(inputPath,outputPath,mainTemplateConfig);
    }*/

    /**
     * @param inputPath 模板文件输入路径
     * @param outputPath 输出路径
     * @param model 数据模型
     * @return:
     * @Author: 谢骏晖
     * @date: 2024/12/5
     * @Description: 生成文件
     */
    public static void doGenerator(String inputPath,String outputPath,Object model) throws IOException,TemplateException {
        Configuration configuration = new Configuration(Configuration.VERSION_2_3_32);

        File templateDir = new File(inputPath).getParentFile();
        configuration.setDirectoryForTemplateLoading(templateDir);

        //设置模板文件使用的字符集
        configuration.setDefaultEncoding("utf-8");

        //创建模板对象，加载指定模板
        String templateName = new File(inputPath).getName();
        Template template = configuration.getTemplate(templateName);

        //生成
        Writer out = new FileWriter(outputPath);
        template.process(model,out);

        out.close();
    }
}
