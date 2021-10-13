package com.framework.dao.authentication;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.framework.entity.authentication.SysUserRole;

import java.util.List;

/**
 * @author Admin
 */
public interface SysUserRoleMapper extends BaseMapper<SysUserRole> {

    /**
     * 根据角色ID查询用户集合
     * @param roleId
     * @return
     */
    List<String> selectUsersByRoleId(String roleId);

}