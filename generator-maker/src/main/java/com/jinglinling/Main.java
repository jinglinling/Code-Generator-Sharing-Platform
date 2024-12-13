package com.jinglinling;

import com.jinglinling.maker.generator.main.MainGenerator;
import freemarker.template.TemplateException;

import java.io.IOException;

/**
 * @Auther 谢骏晖
 * @Date 2024/12/6
 * @Description
 */
public class Main {
    public static void main(String[] args) throws TemplateException, IOException, InterruptedException {
        new MainGenerator().doGenerate();
    }
}
