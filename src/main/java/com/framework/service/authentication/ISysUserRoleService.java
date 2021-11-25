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
     * 根据用户id查询角色集合
     * @param userId
     * @return
     */
    List<String> selectRolesByUserId(String userId);

}
