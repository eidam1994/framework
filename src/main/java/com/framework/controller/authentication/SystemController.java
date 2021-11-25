package com.framework.controller.authentication;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.framework.constant.response.Result;
import com.framework.entity.authentication.SysRole;
import com.framework.entity.authentication.SysUser;
import com.framework.service.authentication.ISysRoleService;
import com.framework.service.authentication.ISysUserRoleService;
import com.framework.service.authentication.ISysUserService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @description: 系统管理
 * @author: xingyuzhang
 * @create: 2020-12-29 16:12
 */
@RestController
public class SystemController {

    @Resource
    private ISysUserService userService;

    @Resource
    private ISysRoleService roleService;

    @Resource
    private ISysUserRoleService userRoleService;

    /**
     * 用户列表展示
     *
     * @return
     */
    @GetMapping("/v1/users")
    public Result getAllUsers(Page<SysUser> userPage) {
        Page<SysUser> page = userService.page(userPage);
        return Result.success(page);
    }

    /**
     * 角色列表展示
     *
     * @return
     */
    @GetMapping("/v1/roles")
    public Result getAllRoles(Page<SysRole> rolePage) {
        Page<SysRole> page = roleService.page(rolePage);
        return Result.success(page);
    }

    /**
     * 修改用户密码
     *
     * @param sysUser
     * @return
     */
    @PostMapping("/v1/users/password")
    public Result updatePassword(SysUser sysUser) {
        return userService.updatePassword(sysUser);
    }


    /**
     * 新增角色
     *
     * @param sysRole
     * @return
     */
    @PostMapping("/v1/roles")
    public Result addUser(SysRole sysRole) {
        if (roleService.save(sysRole)) {
            return Result.success();
        } else {
            return Result.error("Add Role Failure.");
        }
    }

    /**
     * 获取该角色下的所有用户
     *
     * @param roleId
     * @return
     */
    @GetMapping("/v1/usersOfRole")
    public Result getUsersOfRole(String roleId) {
        List<String> userIds = userRoleService.selectUsersByRoleId(roleId);
        List<SysUser> allUsers = userService.list();
        Map<String, Object> userRoleList = new HashMap<>();
        userRoleList.put("allUsers", allUsers);
        userRoleList.put("selectedUsers", userIds);
        return Result.success(userRoleList);
    }

}
