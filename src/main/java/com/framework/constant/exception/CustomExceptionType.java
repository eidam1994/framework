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
    SERVICE_ERROR(400, "Business Errors."),

    /**
     * 业务错误
     */
    UNAUTH_ERROR(403, "User is not logged in."),

    /**
     * 系统报错
     */
    SYSTEM_ERROR(500, "System Errors.");
    
    private Integer code;
    
    private String message;
    
    CustomExceptionType(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
}
