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
public class SysRole implements Serializable {
    /**
     * 角色id
     */
    private Long id;

    /**
     * 角色名称
     */
    private String roleName;

    private static final long serialVersionUID = 1L;
}