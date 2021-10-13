package com.framework.controller.authentication;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.framework.constant.response.Result;
import com.framework.entity.authentication.SysUser;
import com.framework.service.authentication.ISysUserService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class SysUserController {

    @Autowired
    private ISysUserService userService;

    @PostMapping("/list")
    public Result pageList(Page<SysUser> userPage, @RequestBody SysUser user) {
        QueryWrapper<SysUser> userQueryWrapper = new QueryWrapper<>();
        if (StringUtils.isNotEmpty(user.getUsername())) {
            userQueryWrapper.like("username", user.getUsername());
        }
        if (StringUtils.isNotEmpty(user.getPhoneNumber())) {
            userQueryWrapper.like("phone_number", user.getPhoneNumber());
        }
        return Result.success(userService.page(userPage, userQueryWrapper));
    }
}
