package com.framework.controller.authentication;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.framework.constant.response.Result;
import com.framework.entity.authentication.SysUser;
import com.framework.pageDto.authentication.UserPageDTO;
import com.framework.service.authentication.ISysUserService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@RequestMapping("/user")
public class SysUserController {

    @Resource
    private ISysUserService userService;

    /**
     * 用户列表查询
     * @param pageDTO
     * @return
     */
    @PostMapping("/list")
    public Result pageList(@RequestBody UserPageDTO pageDTO) {
        return userService.userList(pageDTO);
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

    @PostMapping("/register")
    public Result register(@RequestBody SysUser user) {
        return userService.register(user);
    }
}
