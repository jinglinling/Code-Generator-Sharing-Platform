package com.jinglinling.web.model.dto.generator;

import lombok.Data;

import java.io.Serializable;
import java.util.Map;

/**
 * @Auther 谢骏晖
 * @Date 2025/1/22
 * @Description 使用代码生成器请求
 */
@Data
public class GeneratorUseRequest implements Serializable {

    //生成器的id
    private Long id;

    //数据模型
    Map<String,Object> dataModel;

    private static final long serialVersionUID = 1L;
}
