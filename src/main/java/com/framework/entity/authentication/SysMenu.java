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
public class SysMenu implements Serializable {
    /**
     * 菜单主键
     */
    @TableId
    private Long id;

    /**
     * 权限或菜单名称
     */
    private String menuName;

    /**
     * 跳转路径
     */
    private String menuUrl;

    /**
     * 菜单类型 admin-管理员菜单 menu-普通菜单 permission-权限
     */
    private String menuType;

    /**
     * 父菜单id
     */
    private Long parentId;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 修改时间
     */
    private Date updateTime;

    private static final long serialVersionUID = 1L;
}