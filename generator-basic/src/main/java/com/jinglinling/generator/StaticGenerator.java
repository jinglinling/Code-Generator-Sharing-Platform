package com.jinglinling.generator;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.util.ArrayUtil;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;

/**
 * @Auther 谢骏晖
 * @Date 2024/12/5
 * @Description 使用Hutool生成(复制)静态文件
 */
public class StaticGenerator {
    public static void copyFileByHutool(String inputPath,String outputPath) {
        FileUtil.copy(inputPath,outputPath,false);
    }

    /*public static void main(String[] args) {
        //获取整个项目的根目录
        String projectPath = System.getProperty("user.dir");
        System.out.println(projectPath);
        File parentFile = new File(projectPath).getParentFile();
        //输入路径：AMC示例代码模板目录
        String inputPath = new File(parentFile,"generator-demo-projects/acm-template").getAbsolutePath();
        //输出路径：直接输出到项目的根目录
        String outputPath = projectPath;
        //copyFileByHutool(inputPath,projectPath);
        copyFilesByRecursive(inputPath,projectPath);
    }*/

    /**
     * @param inputPath
     * @param outputPath
     * @return:
     * @Author: 谢骏晖
     * @date: 2024/12/5
     * @Description: 递归拷贝文件(递归实现，会将输入目录完成拷贝到输出目录下)
     */
    public static void copyFilesByRecursive(String inputPath,String outputPath) {
        File inputFile = new File(inputPath);
        File outputFile = new File(outputPath);
        try {
            copyFileByRecursive(inputFile,outputFile);
        } catch (Exception e) {
            System.out.println("文件复制失败");
            e.printStackTrace();
        }
    }

    /**
     * @param inputFile
     * @param outputFile
     * @return:
     * @Author: 谢骏晖
     * @date: 2024/12/5
     * @Description:
     *  文件A -> 目录B，则文件A放在目录B下
     *  文件A -> 文件B，则文件A覆盖文件B
     *  目录A -> 目录B，则目录A放在目录B下
     *
     *  核心思想:先创建目录，然后遍历目录内的文件，依次复制
     */
    private static void copyFileByRecursive(File inputFile, File outputFile) throws IOException {
        //区分是文件还是目录
        if (inputFile.isDirectory()) {
            System.out.println(inputFile.getName());
            File destOutputFile = new File(outputFile,inputFile.getName());
            //如果是目录，则先创建目标目录
            if (!destOutputFile.exists()) {
                destOutputFile.mkdirs();
            }
            //获取目录下的所有文件和子目录
            File[] files = inputFile.listFiles();
            //无子文件，直接结束
            if (ArrayUtil.isEmpty(files)) {
                return;
            }
            for (File file : files) {
                //递归拷贝下一层文件
                copyFileByRecursive(file,destOutputFile);
            }
        } else {
            //是文件，直接复制到目标目录下
            Path destPath = outputFile.toPath().resolve(inputFile.getName());
            Files.copy(inputFile.toPath(),destPath, StandardCopyOption.REPLACE_EXISTING);
        }
    }
}
