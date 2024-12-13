package com.jinglinling.maker.generator.main;

import freemarker.template.TemplateException;

import java.io.IOException;

/**
 * @Auther 谢骏晖
 * @Date 2024/12/10
 * @Description 测试能否获取到包含配置信息的 meta 对象
 */
public class MainGenerator extends GenerateTemplate{
    @Override
    protected void buildDist(String outputPath, String sourceCopyDestPath, String readMeOutPutPath, String shellOutputFilePath, String jarPath) {
        System.out.println("不生成精简版程序");
    }

    public static void main(String[] args) throws TemplateException, IOException, InterruptedException {
        new MainGenerator().doGenerate();
    }
}