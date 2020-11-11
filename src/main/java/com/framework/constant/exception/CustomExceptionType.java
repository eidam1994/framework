package com.framework.constant.exception;

import lombok.Getter;

/**
 * @description: 自定义异常类型
 * @author: xingyuzhang
 * @create: 2020-09-15 14:50
 */
@Getter
public enum CustomExceptionType {

    /**
     * 业务错误
     */
    SERVICE_ERROR(400, "业务错误"),

    /**
     * 系统报错
     */
    SYSTEM_ERROR(500, "系统错误");
    
    private Integer code;
    
    private String message;
    
    CustomExceptionType(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
}
