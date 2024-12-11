package com.jinglinling.maker.generator.file;

import cn.hutool.core.io.FileUtil;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

import java.io.*;

/**
 * @Auther 谢骏晖
 * @Date 2024/12/5
 * @Description 动态文件生成
 */
public class DynamicFileGenerator {

    /**
     * @param inputPath 模板文件输入路径
     * @param outputPath 输出路径
     * @param model 数据模型
     * @return:
     * @Author: 谢骏晖
     * @date: 2024/12/5
     * @Description: 生成文件
     */
    public static void doGenerator(String inputPath,String outputPath,Object model) throws IOException,TemplateException {
        Configuration configuration = new Configuration(Configuration.VERSION_2_3_32);

        File templateDir = new File(inputPath).getParentFile();
        configuration.setDirectoryForTemplateLoading(templateDir);

        //设置模板文件使用的字符集
        configuration.setDefaultEncoding("utf-8");

        //创建模板对象，加载指定模板
        String templateName = new File(inputPath).getName();
        Template template = configuration.getTemplate(templateName);

        //文件不存在则创建文件和父目录
        if (!FileUtil.exist(outputPath)) {
            FileUtil.touch(outputPath);
        }

        //生成
        try (Writer out = new OutputStreamWriter(new FileOutputStream(outputPath), "UTF-8")) {
            template.process(model, out);
        }
    }
}
