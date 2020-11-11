package com.framework.service.authentication;

import com.baomidou.mybatisplus.extension.service.IService;
import com.framework.entity.authentication.SysUser;

/**
 * @description:
 * @author: xingyuzhang
 * @create: 2020-10-21 14:12
 */
public interface ISysUserService extends IService<SysUser> {

    /**
     * 根据用户名查询用户信息
     * @param username
     * @return
     */
    SysUser findByUsername(String username);

    /**
     * 登录
     * @param username
     * @param password
     * @return
     */
    SysUser login(String username, String password);

    /**
     * 登出系统
     */
    void logout();
    
}
