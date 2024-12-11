package com.jinglinling.maker.generator.file;

import cn.hutool.core.io.FileUtil;

/**
 * @Auther 谢骏晖
 * @Date 2024/12/5
 * @Description 使用Hutool生成(复制)静态文件
 */
public class StaticFileGenerator {
    /**
     * @param inputPath
     * @param outputPath
     * @return:
     * @Author: 谢骏晖
     * @date: 2024/12/10
     * @Description: 拷贝文件（Hutool实现，将输入目录完成拷贝到输出目录下）
     */
    public static void copyFileByHutool(String inputPath,String outputPath) {
        FileUtil.copy(inputPath,outputPath,false);
    }
}
