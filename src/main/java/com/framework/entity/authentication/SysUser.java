package com.framework.entity.authentication;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.framework.constant.common.PageEntity;
import lombok.Data;
import org.springframework.data.annotation.Transient;

/**
 * sys_menu
 * @author 
 */
@Data
public class SysUser implements Serializable {
    /**
     * 主键id
     */
    private String id;

    /**
     * 登录名
     */
    private String loginName;

    /**
     * 用户名
     */
    private String username;

    /**
     * 密码
     */
    private String password;

    /**
     * 邮箱
     */
    private String email;

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
     * 电话号码
     */
    private String phoneNumber;

    /**
     * 角色集合
     */
    @TableField(exist = false)
    private List<String> roleIds;
    
    private static final long serialVersionUID = 1L;

    @Override
    public boolean equals(Object obj) {
        SysUser u = (SysUser) obj;
        return loginName.equals(u.loginName);
    }

    @Override
    public int hashCode() {
        String in = loginName;
        return in.hashCode();
    }
}