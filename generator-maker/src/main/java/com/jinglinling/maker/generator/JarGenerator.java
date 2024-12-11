package com.jinglinling.maker.generator;

import java.io.*;

/**
 * @Auther 谢骏晖
 * @Date 2024/12/11
 * @Description 自动构建jar包
 */
public class JarGenerator {
    public static void doGenerate(String projectDir) throws IOException,InterruptedException {
        //清理不同的构建并打包
        //不同操作系统，执行命令也不同
        String winMavenCommand = "mvn.cmd clean package -DskipTests=true";
        String otherMavenCommand = "mvn clean package -DskipTests=true";
        String mavenCommand = winMavenCommand;

        ProcessBuilder processBuilder = new ProcessBuilder(mavenCommand.split(" "));
        processBuilder.directory(new File(projectDir));

        Process process = processBuilder.start();

        //读取命令输出
        InputStream inputStream = process.getInputStream();
        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
        String line;
        while ((line = reader.readLine()) != null) {
            System.out.println(line);
        }

        //等待命令执行完成
        int exitCode = process.waitFor();
        System.out.println("命令执行结束，退出码：" + exitCode);
    }

    public static void main(String[] args) throws IOException, InterruptedException {
        doGenerate("D:\\ProgramCode\\Java\\eclipse-wokrspace\\Code-Generator-Sharing-Platform\\generator-maker\\generated\\acm-template-pro-generator");
    }
}
