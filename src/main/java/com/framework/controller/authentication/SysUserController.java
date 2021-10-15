package com.framework.controller.authentication;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.framework.constant.response.Result;
import com.framework.entity.authentication.SysUser;
import com.framework.pageDto.UserPageDTO;
import com.framework.service.authentication.ISysUserService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class SysUserController {

    @Autowired
    private ISysUserService userService;

    /**
     * 用户列表查询
     * @param pageDTO
     * @return
     */
    @PostMapping("/list")
    public Result pageList(@RequestBody UserPageDTO pageDTO) {
        QueryWrapper<SysUser> userQueryWrapper = new QueryWrapper<>();
        if (StringUtils.isNotEmpty(pageDTO.getLoginName())) {
            userQueryWrapper.like("login_name", pageDTO.getLoginName());
        }
        if (StringUtils.isNotEmpty(pageDTO.getUsername())) {
            userQueryWrapper.like("username", pageDTO.getUsername());
        }
        if (StringUtils.isNotEmpty(pageDTO.getPhoneNumber())) {
            userQueryWrapper.like("phone_number", pageDTO.getPhoneNumber());
        }
        return Result.success(userService.page(pageDTO, userQueryWrapper));
    }

    /**
     * 新增或修改用户信息
     * @param user
     * @return
     */
    @PostMapping("/save")
    public Result saveUser(@RequestBody SysUser user) {
        return userService.saveUser(user);
    }

    @DeleteMapping("/delete/{id}")
    public Result deleteUser(@PathVariable("id") String id) {
        userService.removeById(id);
        return Result.success();
    }
}
