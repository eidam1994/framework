package com.framework.entity.authentication;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.annotation.JsonFormat;
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
    private String id;

    /**
     * 权限或菜单名称
     */
    private String menuName;

    /**
     * 菜单类型 admin-管理员菜单 menu-普通菜单 button-按钮权限
     */
    private String menuType;

    /**
     * 权限标识
     */
    private String menuCode;

    /**
     * 排序
     */
    private Integer sort;

    /**
     * 父菜单id
     */
    private String parentId;

    /**
     * 创建时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @TableField(fill = FieldFill.INSERT)
    private Date createTime;

    /**
     * 修改时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date updateTime;

    /**
     * 子菜单集合
     */
    @TableField(exist = false)
    private List<SysMenu> children;

    private static final long serialVersionUID = 1L;
}