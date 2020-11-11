package com.framework.controller.authentication;

import com.framework.constant.exception.CustomExceptionType;
import com.framework.constant.response.Result;
import com.framework.entity.authentication.SysUser;
import com.framework.exception.CustomException;
import com.framework.service.authentication.ISysUserService;
import com.framework.utils.UserInfoUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
    public Result login(SysUser user) {
        log.info("————————进入登录————————");
        String username = user.getUsername();
        String password = user.getPassword();
        if (StringUtils.isBlank(username)) {
            return Result.error("用户名不能为空");
        }
        if (StringUtils.isBlank(password)) {
            return Result.error("密码不能为空");
        }
        SysUser sysUser = sysUserService.login(username, password);
        // 登录成功返回用户信息
        return Result.success(sysUser);
    }

    /**
     * 登出系统
     * @return
     */
    @GetMapping("/logout")
    public Result logout() {
        sysUserService.logout();
        return Result.success();
    }

    /**
     * 未登录返回前台信息
     * @return
     */
    @RequestMapping("/unAuth")
    public Result unAuth() {
        return Result.error("用户未登录");
    }

    /**
     * 返回未授权信息
     * @return
     */
    @RequestMapping("/unauthorized")
    public Result unauthorized() {
        return Result.error("用户未授权");
    }

    /**
     * 获取用户信息
     * @return
     */
    @GetMapping("/userInfo")
    public Result getUserInfo() {
        SysUser userInfo = UserInfoUtils.getUserInfo();
        if (Objects.isNull(userInfo)) {
            return Result.error("用户未登录");
        } else {
            return Result.success(userInfo);
        }
    }
}
