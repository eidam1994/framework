package com.framework.controller.authentication;

import cn.dev33.satoken.stp.StpUtil;
import com.framework.constant.exception.CustomExceptionType;
import com.framework.constant.response.Result;
import com.framework.entity.authentication.SysUser;
import com.framework.exception.CustomException;
import com.framework.service.authentication.ISysUserService;
import com.framework.utils.UserInfoUtils;
import com.framework.vo.UserInfoVO;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Objects;

/**
 * @description: 登录控制器
 * @author: xingyuzhang
 * @create: 2020-11-11 15:16
 */
@Slf4j
@RestController
@RequestMapping("/auth")
public class LoginController {
    
    @Resource
    private ISysUserService sysUserService;

    /**
     * 登录系统
     * @param user
     * @return
     */
    @PostMapping("/login")
    public Result login(@RequestBody SysUser user) {
        log.info("————————进入登录————————");
        String loginName = user.getLoginName();
        String password = user.getPassword();
        if (StringUtils.isBlank(loginName)) {
            return Result.error("用户名不能为空");
        }
        if (StringUtils.isBlank(password)) {
            return Result.error("密码不能为空");
        }
        return sysUserService.doLogin(loginName, password);
    }

    @GetMapping("/isLogin")
    public Result isLogin() {
        if (StpUtil.isLogin()) {
            return Result.success();
        } else {
            return Result.error("未登录");
        }
    }

    /**
     * 登出系统
     * @return
     */
    @GetMapping("/logout")
    public Result logout() {
        // 当前会话注销登录
        StpUtil.logout();
        return Result.success();
    }

    /**
     * 获取用户信息
     * @return
     */
    @GetMapping("/userInfo")
    public Result getUserInfo() {
        // 获取当前会话账号id, 并转化为`String`类型
        UserInfoVO userInfo = UserInfoUtils.getUserInfo();
        if (userInfo != null) {
            return Result.success(userInfo);
        } else {
            return Result.error(new CustomException(CustomExceptionType.UNAUTH_ERROR));
        }
    }
}
