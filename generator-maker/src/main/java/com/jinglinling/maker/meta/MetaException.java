package com.jinglinling.maker.meta;

/**
 * @Auther 谢骏晖
 * @Date 2024/12/12
 * @Description 元信息异常类
 */
public class MetaException extends RuntimeException {
    public MetaException(String message) {
        super(message);
    }

    public MetaException(String message, Throwable cause) {
        super(message, cause);
    }
}
