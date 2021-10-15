package com.framework.pageDto.authentication;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.framework.entity.authentication.SysUser;
import lombok.Data;

@Data
public class UserPageDTO extends Page<SysUser> {

    /**
     * 登录名
     */
    private String loginName;

    /**
     * 用户名称
     */
    private String username;

    /**
     * 电话号码
     */
    private String phoneNumber;

}