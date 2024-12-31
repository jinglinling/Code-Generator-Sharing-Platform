package com.jinglinling.maker.template.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @Auther 谢骏晖
 * @Date 2024/12/27
 * @Description 封装所有和文件相关的配置
 */

@Data
public class TemplateMakerFileConfig {
    private List<FileInfoConfig> files;

    private FileGroupConfig fileGroupConfig;

    @NoArgsConstructor
    @Data
    public static class FileInfoConfig {
        private String path;
        private List<FileFilterConfig> FilterConfigList;
    }

    @NoArgsConstructor
    @Data
    public static class FileGroupConfig {
        private String condition;
        private String groupKey;
        private String groupName;
    }
}
