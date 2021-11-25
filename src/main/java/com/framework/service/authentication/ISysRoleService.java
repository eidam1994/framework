package com.framework.service.authentication;

import com.baomidou.mybatisplus.extension.service.IService;
import com.framework.constant.response.Result;
import com.framework.entity.authentication.SysRole;
import com.framework.pageDto.authentication.RolePageDTO;

/**
 * @description:
 * @author: xingyuzhang
 * @create: 2020-10-21 14:11
 */
public interface ISysRoleService extends IService<SysRole> {

    /**
     * 分页查询角色列表
     * @param pageDTO
     * @return
     */
    Result getRolePageList(RolePageDTO pageDTO);

}
