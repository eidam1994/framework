package com.framework.service.authentication;

import com.baomidou.mybatisplus.extension.service.IService;
import com.framework.constant.response.Result;
import com.framework.entity.authentication.SysUserRole;

import java.util.List;

/**
 * @description:
 * @author: xingyuzhang
 * @create: 2020-10-21 14:13
 */
public interface ISysUserRoleService extends IService<SysUserRole> {

    /**
     * 根据角色ID查询用户集合
     * @param roleId
     * @return
     */
    List<String> selectUsersByRoleId(String roleId);

    /**
     * 更新角色所拥有的用户
     * @param roleId
     * @param userIds
     * @return
     */
    Result updateUsersOfRole(String roleId, String userIds);
    
}
