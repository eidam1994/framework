package com.framework.exception;

import com.framework.constant.exception.CustomExceptionType;
import lombok.Getter;

/**
 * @description: 自定义异常
 * @author: xingyuzhang
 * @create: 2020-09-15 15:02
 */
@Getter
public class CustomException extends RuntimeException {
    
    private Integer code;
    
    private String message;
    
    private CustomException() {
        
    }
    
    public CustomException(CustomExceptionType customExceptionType) {
        this.code = customExceptionType.getCode();
        this.message = customExceptionType.getMessage();
    }
    
    public CustomException(CustomExceptionType customExceptionType, String message) {
        this.code = customExceptionType.getCode();
        this.message = message;
    }
}
