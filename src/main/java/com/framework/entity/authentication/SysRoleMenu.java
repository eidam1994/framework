package com.framework.entity.authentication;

import java.io.Serializable;
import java.util.Date;

import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

/**
 * sys_menu
 * @author 
 */
@Data
public class SysRoleMenu implements Serializable {
    /**
     * 角色id
     */
    private String roleId;

    /**
     * 菜单id
     */
    private String menuId;

    private static final long serialVersionUID = 1L;
}