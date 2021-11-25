package com.framework.service.authentication;

import com.baomidou.mybatisplus.extension.service.IService;
import com.framework.entity.authentication.SysRoleMenu;

import java.util.List;

/**
 * @description:
 * @author: xingyuzhang
 * @create: 2020-10-21 14:10
 */
public interface ISysRoleMenuService extends IService<SysRoleMenu> {

    /**
     * 根据角色查询菜单权限
     * @param roleId
     * @return
     */
    List<String> selectMenusByRole(String roleId);

}
