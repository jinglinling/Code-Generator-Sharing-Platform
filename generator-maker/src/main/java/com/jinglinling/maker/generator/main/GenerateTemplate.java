package com.jinglinling.maker.generator.main;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.io.resource.ClassPathResource;
import cn.hutool.core.util.StrUtil;
import cn.hutool.core.util.ZipUtil;
import com.jinglinling.maker.generator.JarGenerator;
import com.jinglinling.maker.generator.ScriptGenerator;
import com.jinglinling.maker.generator.file.DynamicFileGenerator;
import com.jinglinling.maker.meta.Meta;
import com.jinglinling.maker.meta.MetaManager;
import freemarker.template.TemplateException;

import java.io.File;
import java.io.IOException;

/**
 * @Auther 谢骏晖
 * @Date 2024/12/13
 * @Description 抽象生成器程序的流程
 */
public abstract class GenerateTemplate {
    public void doGenerate() throws IOException, TemplateException, InterruptedException {
        Meta meta = MetaManager.getMetaObject();

        //输出根路径
        String projectPath = System.getProperty("user.dir");
        String outputPath = projectPath + File.separator + "generated" + File.separator + meta.getName();
        if (!FileUtil.exist(outputPath)) {
            FileUtil.mkdir(outputPath);
        }

        //复制原始文件
        String sourceCopyDestPath = copySource(meta, outputPath);

        //代码生成
        String readMeOutPutPath = generateCode(meta, outputPath);

        //构建jar包
        String jarPath = buildJar(outputPath,meta);

        //封装脚本
        String shellOutputFilePath = buildScript(outputPath, jarPath);

        //生成精简版的程序(产物包)
        buildDist(outputPath, sourceCopyDestPath, readMeOutPutPath, shellOutputFilePath, jarPath);
    }

    //复制原始文件
    protected String copySource(Meta meta, String outputPath) {
        String sourceRootPath = meta.getFileConfig().getSourceRootPath();
        String sourceCopyDestPath = outputPath + File.separator + ".source";
        FileUtil.copy(sourceRootPath,sourceCopyDestPath,false);
        return sourceCopyDestPath;
    }

    //代码生成
    protected String generateCode(Meta meta, String outputPath) throws IOException, TemplateException {
        //读取resources目录
        ClassPathResource classPathResource = new ClassPathResource("");
        String inputResourcePath = classPathResource.getAbsolutePath();

        //Java包基础路径
        String outputBasePackage = meta.getBasePackage();
        String outputBasePackagePath = StrUtil.join("/",StrUtil.split(outputBasePackage,"."));
        String outputBaseJavaPackagePath = outputPath + File.separator + "src/main/java/" + outputBasePackagePath;

        String inputFilePath;
        String outputFilePath;

        //model.DataModel
        inputFilePath = inputResourcePath + File.separator + "templates/java/model/DataModel.java.ftl";
        outputFilePath = outputBaseJavaPackagePath + "/model/DataModel.java";
        DynamicFileGenerator.doGenerator(inputFilePath,outputFilePath, meta);

        // generator.MainGenerator
        inputFilePath = inputResourcePath + File.separator + "templates/java/generator/MainGenerator.java.ftl";
        outputFilePath = outputBaseJavaPackagePath + "/generator/MainGenerator.java";
        DynamicFileGenerator.doGenerator(inputFilePath , outputFilePath, meta);

        // cli.command.ConfigCommand
        inputFilePath = inputResourcePath + File.separator + "templates/java/cli/command/ConfigCommand.java.ftl";
        outputFilePath = outputBaseJavaPackagePath + "/cli/command/ConfigCommand.java";
        DynamicFileGenerator.doGenerator(inputFilePath , outputFilePath, meta);

        // cli.command.GenerateCommand
        inputFilePath = inputResourcePath + File.separator + "templates/java/cli/command/GenerateCommand.java.ftl";
        outputFilePath = outputBaseJavaPackagePath + "/cli/command/GenerateCommand.java";
        DynamicFileGenerator.doGenerator(inputFilePath , outputFilePath, meta);

        // cli.command.ListCommand
        inputFilePath = inputResourcePath + File.separator + "templates/java/cli/command/ListCommand.java.ftl";
        outputFilePath = outputBaseJavaPackagePath + "/cli/command/ListCommand.java";
        DynamicFileGenerator.doGenerator(inputFilePath , outputFilePath, meta);

        // cli.CommandExecutor
        inputFilePath = inputResourcePath + File.separator + "templates/java/cli/CommandExecutor.java.ftl";
        outputFilePath = outputBaseJavaPackagePath + "/cli/CommandExecutor.java";
        DynamicFileGenerator.doGenerator(inputFilePath , outputFilePath, meta);

        // Main
        inputFilePath = inputResourcePath + File.separator + "templates/java/Main.java.ftl";
        outputFilePath = outputBaseJavaPackagePath + "/Main.java";
        DynamicFileGenerator.doGenerator(inputFilePath , outputFilePath, meta);

        // generator.DynamicGenerator
        inputFilePath = inputResourcePath + File.separator + "templates/java/generator/DynamicGenerator.java.ftl";
        outputFilePath = outputBaseJavaPackagePath + "/generator/DynamicGenerator.java";
        DynamicFileGenerator.doGenerator(inputFilePath , outputFilePath, meta);

        // generator.StaticGenerator
        inputFilePath = inputResourcePath + File.separator + "templates/java/generator/StaticGenerator.java.ftl";
        outputFilePath = outputBaseJavaPackagePath + "/generator/StaticGenerator.java";
        DynamicFileGenerator.doGenerator(inputFilePath , outputFilePath, meta);

        // pom.xml
        inputFilePath = inputResourcePath + File.separator + "templates/pom.xml.ftl";
        outputFilePath = outputPath + File.separator + "pom.xml";
        DynamicFileGenerator.doGenerator(inputFilePath , outputFilePath, meta);

        // README.md
        inputFilePath = inputResourcePath + File.separator + "templates/README.md.ftl";
        String readMeOutPutPath = outputPath + File.separator + "README.md";
        outputFilePath = readMeOutPutPath;
        DynamicFileGenerator.doGenerator(inputFilePath , outputFilePath, meta);
        return readMeOutPutPath;
    }

    // 构建 jar 包
    protected String buildJar(String outputPath,Meta meta) throws IOException, InterruptedException {
        JarGenerator.doGenerate(outputPath);
        String jarName = String.format("%s-%s-jar-with-dependencies.jar", meta.getName(), meta.getVersion());
        String jarPath = "target/" + jarName;
        return jarPath;
    }

    //生成精简版的程序 (产物包)
    protected String buildDist(String outputPath, String sourceCopyDestPath, String readMeOutPutPath, String shellOutputFilePath, String jarPath) {
        String distOutputPath = outputPath + "-dist";
        //拷贝jar包
        String targetAbsolutePath = distOutputPath + File.separator + "target";
        FileUtil.mkdir(targetAbsolutePath);
        String jarAbsolutePath = outputPath + File.separator + jarPath;
        FileUtil.copy(jarAbsolutePath,targetAbsolutePath,true);
        //拷贝脚本文件
        FileUtil.copy(shellOutputFilePath,distOutputPath,true);
        FileUtil.copy(shellOutputFilePath + ".bat",distOutputPath,true);
        //拷贝源模板文件
        FileUtil.copy(sourceCopyDestPath,distOutputPath,true);
        //拷贝README.md文件
        FileUtil.copy(readMeOutPutPath,distOutputPath,true);
        return distOutputPath;
    }

    // 封装脚本
    protected String buildScript(String outputPath, String jarPath) {
        String shellOutputFilePath = outputPath + File.separator + "generator";
        ScriptGenerator.doGenerate(shellOutputFilePath, jarPath);
        return shellOutputFilePath;
    }

    /**
     * 制作压缩包
     * @param outputPath
     * @return 压缩包路径
     */
    protected String buildZip(String outputPath) {
        String zipPath = outputPath + ".zip";
        ZipUtil.zip(outputPath,zipPath);
        return zipPath;
    }
}
