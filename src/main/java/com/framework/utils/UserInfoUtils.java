package com.framework.utils;

import com.framework.entity.authentication.SysUser;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;

/**
 * @description: 用户信息工具类
 * @author: xingyuzhang
 * @create: 2020-11-11 16:02
 */
public class UserInfoUtils {

    /**
     * 获取当前用户信息
     * @return
     */
    public static SysUser getUserInfo() {
        Subject subject = SecurityUtils.getSubject();
        if (subject.getSession() != null) {
            SysUser userInfo = (SysUser) subject.getPrincipals().getPrimaryPrincipal();
            return userInfo;
        } else {
            return null;
        }
    }
    
}
