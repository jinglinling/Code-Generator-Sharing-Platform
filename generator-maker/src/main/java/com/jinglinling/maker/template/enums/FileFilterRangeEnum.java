package com.jinglinling.maker.template.enums;

import cn.hutool.core.util.ObjectUtil;
import lombok.Getter;

/**
 * @Auther 谢骏晖
 * @Date 2024/12/27
 * @Description 文件过滤配置枚举
 */

@Getter
public enum FileFilterRangeEnum {
    FILE_NAME("文件名称", "fileName"),
    FILE_CONTENT("文件内容", "fileContent");

    private final String test;
    private final String value;

    FileFilterRangeEnum(String test, String value) {
        this.test = test;
        this.value = value;
    }

    /**
     * @param value
     * @return:
     * @Author: 谢骏晖
     * @date: 2024/12/27
     * @Description: 根据 value 获取枚举
     */
    public static FileFilterRangeEnum getEnumByValue(String value) {
        if (ObjectUtil.isEmpty(value)) {
            return null;
        }
        for (FileFilterRangeEnum anEnum : FileFilterRangeEnum.values()) {
            if (anEnum.value.equals(value)) {
                return anEnum;
            }
        }
        return null;
    }
}
