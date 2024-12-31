package com.jinglinling.maker.template;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.io.FileUtil;
import com.jinglinling.maker.template.enums.FileFilterRangeEnum;
import com.jinglinling.maker.template.enums.FileFilterRuleEnum;
import com.jinglinling.maker.template.model.FileFilterConfig;

import java.io.File;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @Auther 谢骏晖
 * @Date 2024/12/27
 * @Description 文件过滤
 */
public class FileFilter {

    /**
     * @param fileFilterConfigList 过滤规则
     * @param file 单个文件
     * @return: 是否保留该文件
     * @Author: 谢骏晖
     * @date: 2024/12/27
     * @Description: 单个文件过滤
     */
    public static boolean doSingleFileFilter(List<FileFilterConfig> fileFilterConfigList, File file) {
        String fileName = file.getName();
        String fileContent = FileUtil.readUtf8String(file);

        //所有过滤器的校验结束的结果
        boolean result = true;

        if (CollUtil.isEmpty(fileFilterConfigList)) {
            return true;
        }

        for (FileFilterConfig fileFilterConfig : fileFilterConfigList) {
            String range = fileFilterConfig.getRange();
            String rule = fileFilterConfig.getRule();
            String value = fileFilterConfig.getValue();

            FileFilterRangeEnum fileFilterRangeEnum = FileFilterRangeEnum.getEnumByValue(range);
            if (fileFilterRangeEnum == null) {
                continue;
            }

            //要过滤的内容
            String content = fileName;
            switch (fileFilterRangeEnum) {
                case FILE_NAME:
                    content = fileName;
                    break;
                case FILE_CONTENT:
                    content = fileContent;
                    break;
                default:
            }

            FileFilterRuleEnum fileFilterRuleEnum = FileFilterRuleEnum.getEnumByValue(rule);
            if (fileFilterRuleEnum == null) {
                continue;
            }
            switch (fileFilterRuleEnum) {
                case CONTAINS:
                    result = content.contains(value);
                    break;
                case STARTS_WITH:
                    result = content.startsWith(value);
                    break;
                case ENDS_WITH:
                    result = content.endsWith(value);
                    break;
                case REGEX:
                    result = content.matches(value);
                    break;
                case EQUALS:
                    result = content.equals(value);
                    break;
                default:
            }

            //有一个不满足，直接返回false
            if (!result) {
                return false;
            }
        }
        //都满足
        return true;
    }

    /**
     * @param filePath
     * @param fileFilterConfigList
     * @return:
     * @Author: 谢骏晖
     * @date: 2024/12/27
     * @Description: 对某个文件或目录进行过滤，返回文件列表(对多文件过滤)
     */
    public static List<File> doFilter(String filePath,List<FileFilterConfig> fileFilterConfigList) {
        //根据路径获取所有文件
        List<File> fileList = FileUtil.loopFiles(filePath);
        return fileList.stream()
                .filter(file -> doSingleFileFilter(fileFilterConfigList,file))
                .collect(Collectors.toList());
    }
}
