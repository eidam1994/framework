package com.framework.config.system;

import cn.dev33.satoken.exception.DisableLoginException;
import cn.dev33.satoken.exception.NotLoginException;
import cn.dev33.satoken.exception.NotPermissionException;
import cn.dev33.satoken.exception.NotRoleException;
import com.framework.constant.exception.CustomExceptionType;
import com.framework.constant.response.Result;
import com.framework.exception.CustomException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 全局异常处理
 */
@ControllerAdvice
public class GlobalException {

    // 全局异常拦截（拦截项目中的所有异常）
    @ResponseBody
    @ExceptionHandler
    public Result handlerException(Exception e, HttpServletRequest request, HttpServletResponse response)
            throws Exception {

        // 打印堆栈，以供调试
        e.printStackTrace();

        // 不同异常返回不同状态码
        Result result = null;
        if (e instanceof NotLoginException) {    // 如果是未登录异常
            result = Result.error(new CustomException(CustomExceptionType.UNAUTH_ERROR, "用户未登录"));
        }
//        else if (e instanceof NotRoleException) {        // 如果是角色异常
//            NotRoleException ee = (NotRoleException) e;
//            aj = AjaxJson.getNotJur("无此角色：" + ee.getRole());
//        } else if (e instanceof NotPermissionException) {    // 如果是权限异常
//            NotPermissionException ee = (NotPermissionException) e;
//            aj = AjaxJson.getNotJur("无此权限：" + ee.getCode());
//        } else if (e instanceof DisableLoginException) {    // 如果是被封禁异常
//            DisableLoginException ee = (DisableLoginException) e;
//            aj = AjaxJson.getNotJur("账号被封禁：" + ee.getDisableTime() + "秒后解封");
//        } else {    // 普通异常, 输出：500 + 异常信息
//            aj = AjaxJson.getError(e.getMessage());
//        }

        // 返回给前端
        return result;
    }

}
