package com.framework.exception;

import com.framework.constant.exception.CustomExceptionType;
import com.framework.constant.response.Result;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @description: 全局异常处理器
 * @author: xingyuzhang
 * @create: 2020-09-15 15:58
 */
@ControllerAdvice
public class WebExceptionHandler {

    /**
     * 捕获抛出的自定义异常
     * @param e
     * @return
     */
    @ResponseBody
    @ExceptionHandler(CustomException.class)
    public Result customException(CustomException e) {
        if (CustomExceptionType.SYSTEM_ERROR.getCode().equals(e.getCode())) {
            // TODO: 2020/9/15 系统内部错误进行持久化处理，方便运维人员处理
        }
        return Result.error(e);
    }

    /**
     * 捕获未知的异常
     * @param e
     * @return
     */
    @ResponseBody
    @ExceptionHandler(Exception.class)
    public Result exception(Exception e) {
        // TODO: 2020/9/15 将信息持久化处理
        return Result.error(new CustomException(CustomExceptionType.SYSTEM_ERROR));
    }
    
}
