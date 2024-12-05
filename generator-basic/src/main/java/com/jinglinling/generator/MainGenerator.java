package com.jinglinling.generator;

import com.jinglinling.model.MainTemplateConfig;
import freemarker.template.TemplateException;

import java.io.File;
import java.io.IOException;

/**
 * @Auther 谢骏晖
 * @Date 2024/12/5
 * @Description 核心生成器
 */
public class MainGenerator {
    public static void doGenerator(Object model) throws IOException, TemplateException {
        String projectPath = System.getProperty("user.dir");
        //整个项目的根路径
        File parentFile = new File(projectPath).getParentFile();
        //输入路径
        String inputPath = new File(parentFile,"generator-demo-projects/acm-template").getAbsolutePath();
        String outputPath = projectPath;

        //生成静态文件
        StaticGenerator.copyFilesByRecursive(inputPath,outputPath);

        //生成动态文件
        String inputDynamicFilePath = projectPath + File.separator + "src/main/resources/templates/MainTemplate.java.ftl";
        String outputDynamicFilePath = outputPath + File.separator + "acm-template/src/com/jinglinling/acm/MainTemplate.java";
        DynamicGenerator.doGenerator(inputDynamicFilePath,outputDynamicFilePath,model);
    }

    public static void main(String[] args) throws IOException,TemplateException {
        MainTemplateConfig mainTemplateConfig = new MainTemplateConfig();
        mainTemplateConfig.setAuthor("jinglinling");
        mainTemplateConfig.setLoop(false);
        mainTemplateConfig.setOutputText("求和结果： ");
        doGenerator(mainTemplateConfig);
    }
}