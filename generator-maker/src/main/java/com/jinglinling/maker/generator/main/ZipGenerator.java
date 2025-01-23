package com.jinglinling.maker.generator.main;

/**
 * @Auther 谢骏晖
 * @Date 2025/1/14
 * @Description 生成代码生成器压缩包
 */
public class ZipGenerator extends GenerateTemplate {
    @Override
    protected String buildDist(String outputPath, String sourceCopyDestPath, String jarPath, String shellOutputFilePath) {
        String distPath = super.buildDist(outputPath, sourceCopyDestPath, jarPath, shellOutputFilePath);
        return super.buildZip(distPath);
    }
}
