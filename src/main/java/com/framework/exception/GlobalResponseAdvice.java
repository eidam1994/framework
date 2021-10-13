package com.framework.exception;

import com.framework.constant.exception.CustomExceptionType;
import com.framework.constant.response.Result;
import org.springframework.core.MethodParameter;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

/**
 * @description: 全局返回实体类拦截
 * @author: xingyuzhang
 * @create: 2020-09-16 10:45
 */
@Component
@ControllerAdvice
public class GlobalResponseAdvice implements ResponseBodyAdvice {

    @Override
    public boolean supports(MethodParameter methodParameter, Class aClass) {
        return false;
    }

    @Override
    public Object beforeBodyWrite(Object body, MethodParameter methodParameter, MediaType mediaType, Class aClass, ServerHttpRequest serverHttpRequest, ServerHttpResponse serverHttpResponse) {
        // 如果响应数据是JSON类型
        if (mediaType.equalsTypeAndSubtype(MediaType.APPLICATION_JSON)) {
            if (body instanceof Result) {
                Result result = (Result) body;
                // 系统错误，特殊处理
                if (!CustomExceptionType.SYSTEM_ERROR.getCode().equals(result.getCode())) {
                    serverHttpResponse.setStatusCode(HttpStatus.valueOf(result.getCode()));
                }
                return body;
            } else {
                serverHttpResponse.setStatusCode(HttpStatus.OK);
                return Result.success(body);
            }
        }
        return body;
    }
}
