package com.framework.pageDto.authentication;

import com.framework.constant.common.PageEntity;
import lombok.Data;

@Data
public class RolePageDTO extends PageEntity {

    /**
     * 角色名称
     */
    private String roleName;

}
