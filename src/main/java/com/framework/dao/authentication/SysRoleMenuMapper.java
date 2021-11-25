package com.framework.dao.authentication;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.framework.entity.authentication.SysRoleMenu;

import java.util.List;

/**
 * @author Admin
 */
public interface SysRoleMenuMapper extends BaseMapper<SysRoleMenu> {

    /**
     * 根据角色id查询菜单权限
     * @param roleId
     * @return
     */
    List<String> selectMenusByRole(String roleId);
    
}