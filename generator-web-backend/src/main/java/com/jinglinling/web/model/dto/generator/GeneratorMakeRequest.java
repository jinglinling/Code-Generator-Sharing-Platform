package com.jinglinling.web.model.dto.generator;

import com.jinglinling.maker.meta.Meta;
import lombok.Data;

import java.io.Serializable;

/**
 * @Auther 谢骏晖
 * @Date 2025/2/6
 * @Description 制作代码生成器请求
 */
@Data
public class GeneratorMakeRequest implements Serializable {
    private static final long serialVersionUID = 1L;

    //压缩文件路径
    private String zipFilePath;

    //元信息
    private Meta meta;
}
