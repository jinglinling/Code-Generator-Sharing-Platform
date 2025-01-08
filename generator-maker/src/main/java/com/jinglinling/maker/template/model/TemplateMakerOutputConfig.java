package com.jinglinling.maker.template.model;

import lombok.Data;

/**
 * @Auther 谢骏晖
 * @Date 2025/1/7
 * @Description 输出配置类
 */
@Data
public class TemplateMakerOutputConfig {
    // 从未分组文件中移除组内的同名文件
    private boolean removeGroupFilesFromRoot = true;
}
