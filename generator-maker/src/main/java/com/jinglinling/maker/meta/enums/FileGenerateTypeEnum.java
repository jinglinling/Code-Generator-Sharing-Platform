package com.jinglinling.maker.meta.enums;

/**
 * @Auther 谢骏晖
 * @Date 2024/12/13
 * @Description 文件生成类型枚举
 */
public enum FileGenerateTypeEnum {
    DYNAMIC("动态","dynamic"),
    STATIC("静态","static");

    private final String text;
    private final String value;

    FileGenerateTypeEnum(String text, String value) {
        this.text = text;
        this.value = value;
    }

    public String getText() {
        return text;
    }

    public String getValue() {
        return value;
    }
}
