package com.jinglinling.model;

import lombok.Data;

/**
 * @Auther 谢骏晖
 * @Date 2024/12/5
 * @Description 动态模板配置
 */
@Data
public class MainTemplateConfig {
    //是否生成循环
    private boolean loop;

    //作者注释
    private String author = "谢骏晖";

    //输出信息
    private String outputText = "sum = ";
}
