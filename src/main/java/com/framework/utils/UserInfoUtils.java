package com.framework.utils;

import cn.dev33.satoken.exception.NotLoginException;
import cn.dev33.satoken.stp.StpUtil;
import com.framework.entity.authentication.SysUser;
import com.framework.service.authentication.impl.SysUserServiceImpl;
import com.framework.vo.UserInfoVO;
import org.springframework.beans.BeanUtils;

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
    public static UserInfoVO getUserInfo() {
        try {
            long id = StpUtil.getLoginIdAsLong();
            SysUserServiceImpl userService = SpringUtil.getBean("sysUserServiceImpl", SysUserServiceImpl.class);
            SysUser user = userService.getById(id);
            UserInfoVO userInfoVO = new UserInfoVO();
            BeanUtils.copyProperties(user, userInfoVO);
            return userInfoVO;
        } catch (NotLoginException e) {
            return null;
        }
    }
    
}
