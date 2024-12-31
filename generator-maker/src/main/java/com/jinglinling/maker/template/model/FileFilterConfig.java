package com.jinglinling.maker.template.model;

import lombok.Builder;
import lombok.Data;

/**
 * @Auther 谢骏晖
 * @Date 2024/12/27
 * @Description 文件过滤配置
 */

@Data
@Builder
public class FileFilterConfig {
    //过滤范围：根据文件名称、或者文件内容过滤。
    private String range;

    //过滤规则：包含 contains、前缀匹配 startsWith、后缀匹配 endsWith、正则 regex、相等 equals。
    private String rule;

    //过滤值
    private String value;
}
