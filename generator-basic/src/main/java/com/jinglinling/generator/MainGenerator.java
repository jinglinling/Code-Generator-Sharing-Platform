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
        String inputRootPath = "D:\\ProgramCode\\Java\\eclipse-wokrspace\\Code-Generator-Sharing-Platform\\generator-demo-projects\\acm-template-pro";
        String outputRootPath = "D:\\ProgramCode\\Java\\eclipse-wokrspace\\Code-Generator-Sharing-Platform\\acm-template-pro";

        String inputPath;
        String outputPath;

        inputPath = new File(inputRootPath,"src/com/jinglinling/acm/MainTemplate.java.ftl").getAbsolutePath();
        outputPath = new File(outputRootPath,"src/com/jinglinling/acm/MainTemplate.java").getAbsolutePath();
        DynamicGenerator.doGenerator(inputPath,outputPath,model);

        inputPath = new File(inputRootPath,".gitignore").getAbsolutePath();
        outputPath = new File(outputRootPath,".gitignore").getAbsolutePath();
        StaticGenerator.copyFileByHutool(inputPath,outputPath);

        inputPath = new File(inputRootPath,"README.md").getAbsolutePath();
        outputPath = new File(outputRootPath,"README.md").getAbsolutePath();
        StaticGenerator.copyFileByHutool(inputPath,outputPath);
    }

    public static void main(String[] args) throws IOException,TemplateException {
        MainTemplateConfig mainTemplateConfig = new MainTemplateConfig();
        mainTemplateConfig.setAuthor("jinglinling");
        mainTemplateConfig.setLoop(false);
        mainTemplateConfig.setOutputText("求和结果： ");
        doGenerator(mainTemplateConfig);
    }
}
