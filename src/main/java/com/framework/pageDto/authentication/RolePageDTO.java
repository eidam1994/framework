package com.framework.pageDto.authentication;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.framework.entity.authentication.SysRole;
import lombok.Data;

@Data
public class RolePageDTO extends Page<SysRole> {

    /**
     * 角色名称
     */
    private String roleName;

}
