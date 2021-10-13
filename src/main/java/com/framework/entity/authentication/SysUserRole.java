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
public class SysUserRole implements Serializable {
    /**
     * 用户id
     */
    private Long userId;

    /**
     * 角色id
     */
    private Long roleId;

    private static final long serialVersionUID = 1L;
}