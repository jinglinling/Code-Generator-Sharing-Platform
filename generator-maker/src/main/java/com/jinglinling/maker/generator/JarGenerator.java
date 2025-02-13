package com.jinglinling.maker.generator;

import java.io.*;
import java.util.Map;

/**
 * @Auther 谢骏晖
 * @Date 2024/12/11
 * @Description 自动构建jar包
 */
public class JarGenerator {
    public static void doGenerate(String projectDir) throws IOException,InterruptedException {
        //清理不同的构建并打包
        //不同操作系统，执行命令也不同
        //windows环境
        String winMavenCommand = "mvn.cmd clean package -DskipTests=true";
        //linux环境
        String otherMavenCommand = "mvn clean package -DskipTests=true";
        String mavenCommand = otherMavenCommand;

        //这里一定要拆分！
        ProcessBuilder processBuilder = new ProcessBuilder(mavenCommand.split(" "));
        processBuilder.directory(new File(projectDir));
        Map<String, String> environment = processBuilder.environment();
        System.out.println(environment);
        Process process = processBuilder.start();

        //读取命令的输出
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
}
