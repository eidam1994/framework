package com.framework.dao.authentication;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.framework.entity.authentication.SysRole;
import com.framework.pageDto.authentication.RolePageDTO;

import java.util.List;

/**
 * @author Admin
 */
public interface SysRoleMapper extends BaseMapper<SysRole> {

    /**
     * 分页查询角色列表
     * @param pageDTO
     * @return
     */
    List<SysRole> selectRolePage(RolePageDTO pageDTO);

}