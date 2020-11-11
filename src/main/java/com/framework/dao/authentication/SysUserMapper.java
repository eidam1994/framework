package com.framework.dao.authentication;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.framework.entity.authentication.SysUser;

/**
 * @author Admin
 */
public interface SysUserMapper extends BaseMapper<SysUser> {

    /**
     * 根据用户名查询用户信息
     * @param username
     * @return
     */
    SysUser findByUsername(String username);
    
}