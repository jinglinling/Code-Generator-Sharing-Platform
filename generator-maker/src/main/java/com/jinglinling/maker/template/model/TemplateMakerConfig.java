package com.jinglinling.maker.template.model;

import com.jinglinling.maker.meta.Meta;
import lombok.Data;

/**
 * @Auther 谢骏晖
 * @Date 2025/1/7
 * @Description 模板制作配置
 */
@Data
public class TemplateMakerConfig {
    private Long id;
    private Meta meta = new Meta();
    private String originProjectPath;
    TemplateMakerFileConfig fileConfig  = new TemplateMakerFileConfig();
    TemplateMakerModelConfig modelConfig  = new TemplateMakerModelConfig();
    TemplateMakerOutputConfig outputConfig = new TemplateMakerOutputConfig();
}
