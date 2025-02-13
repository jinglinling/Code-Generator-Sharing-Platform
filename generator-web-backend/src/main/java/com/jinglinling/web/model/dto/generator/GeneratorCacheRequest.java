package com.jinglinling.web.model.dto.generator;

import lombok.Data;

import java.io.Serializable;

/**
 * @Auther 谢骏晖
 * @Date 2025/2/7
 * @Description 缓存代码生成器请求
 */
@Data
public class GeneratorCacheRequest implements Serializable {
    private static long serialVersionUID = 1L;

    //生成器的id
    private long id;
}
