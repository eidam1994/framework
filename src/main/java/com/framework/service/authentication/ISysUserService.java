package com.framework.service.authentication;

import com.baomidou.mybatisplus.extension.service.IService;
import com.framework.constant.response.Result;
import com.framework.entity.authentication.SysUser;
import com.framework.dto.authentication.UserPageDTO;

/**
 * @description:
 * @author: xingyuzhang
 * @create: 2020-10-21 14:12
 */
public interface ISysUserService extends IService<SysUser> {

    /**
     * 登录
     * @param username
     * @param password
     * @return
     */
    Result doLogin(String username, String password);

    /**
     * 根据用户名查询用户信息
     * @param username
     * @return
     */
    SysUser findByUsername(String username);

    /**
     * 用户列表查询
     * @param pageDTO
     * @return
     */
    Result userList(UserPageDTO pageDTO);

    /**
     * 修改密码
     * @param sysUser
     * @return
     */
    Result updatePassword(SysUser sysUser);

    /**
     * 新增用户
     * @param sysUser
     * @return
     */
    Result saveUser(SysUser sysUser);

    /**
     * 用户注册
     * @param sysUser
     * @return
     */
    Result register(SysUser sysUser);
    
}
