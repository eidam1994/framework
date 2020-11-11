package com.framework.constant.response;

import com.framework.constant.exception.CustomExceptionType;
import com.framework.exception.CustomException;
import lombok.Data;

/**
 * @description: 返回前端统一数据结构
 * @author: xingyuzhang
 * @create: 2020-09-15 15:12
 */
@Data
public class Result {

    /**
     * 请求是否成功
     */
    private Boolean isSuccess;

    /**
     * 请求返回代码
     */
    private Integer code;

    /**
     * 请求返回信息
     */
    private String message;

    /**
     * 请求返回数据
     */
    private Object data;
    
    private Result() {}
    
    public static Result success() {
        Result result = new Result();
        result.setIsSuccess(true);
        result.setCode(200);
        result.setMessage("success");
        return result;
    }

    public static Result success(String message) {
        Result result = new Result();
        result.setIsSuccess(true);
        result.setCode(200);
        result.setMessage(message);
        return result;
    }
    
    public static Result success(Object data) {
        Result result = new Result();
        result.setIsSuccess(true);
        result.setCode(200);
        result.setMessage("success");
        result.setData(data);
        return result;
    }
    
    public static Result error(CustomException e) {
        Result result = new Result();
        result.setIsSuccess(false);
        result.setCode(e.getCode());
        result.setMessage(e.getMessage());
        return result;
    }
    
    public static Result error(CustomException e, String errorMessage) {
        Result result = new Result();
        result.setIsSuccess(false);
        result.setCode(e.getCode());
        result.setMessage(errorMessage);
        return result;
    }
    
    public static Result error(String errorMessage) {
        Result result = new Result();
        result.setIsSuccess(false);
        // 业务错误
        result.setCode(CustomExceptionType.SERVICE_ERROR.getCode());
        result.setMessage(errorMessage);
        return result;
    }
}
