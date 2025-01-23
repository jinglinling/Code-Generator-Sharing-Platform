package com.jinglinling.maker.generator.main;

/**
 * @Auther 谢骏晖
 * @Date 2024/12/10
 * @Description 测试能否获取到包含配置信息的 meta 对象
 */
public class MainGenerator extends GenerateTemplate{
    @Override
    protected String buildDist(String outputPath, String sourceCopyDestPath, String jarPath, String shellOutputFilePath) {
        System.out.println("不生成精简版程序");
        return "";
    }
}