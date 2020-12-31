package com.framework.service.authentication.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.framework.constant.exception.CustomExceptionType;
import com.framework.constant.response.Result;
import com.framework.dao.authentication.SysUserMapper;
import com.framework.entity.authentication.SysUser;
import com.framework.exception.CustomException;
import com.framework.service.authentication.ISysUserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.ByteSource;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @description:
 * @author: xingyuzhang
 * @create: 2020-10-21 14:12
 */
@Service
public class SysUserServiceImpl extends ServiceImpl<SysUserMapper, SysUser> implements ISysUserService {
    
    @Resource
    private SysUserMapper sysUserMapper;
    
    @Override
    public SysUser findByUsername(String username) {
        return sysUserMapper.findByUsername(username);
    }

    @Override
    public SysUser login(String username, String password) {
        // 获取Subject实例对象，用户实例
        Subject currentUser = SecurityUtils.getSubject();
        // 将用户名和密码封装到UsernamePasswordToken
        UsernamePasswordToken token = new UsernamePasswordToken(username, password);
        SysUser user;
        try {
            // 传到 MyShiroRealm 类中的方法进行认证
            currentUser.login(token);
            // 构建用户信息返回给前端
            user = (SysUser) currentUser.getPrincipals().getPrimaryPrincipal();
        } catch (UnknownAccountException e) {
            log.error("账号不存在:");
            throw new CustomException(CustomExceptionType.SERVICE_ERROR, "User does not exist.");
        } catch (IncorrectCredentialsException e) {
            log.error("密码错误:");
            throw new CustomException(CustomExceptionType.SERVICE_ERROR, "The password is incorrect.");
        } catch (AuthenticationException e) {
            log.error("身份验证异常:", e);
            throw new CustomException(CustomExceptionType.SERVICE_ERROR, "System Login Exception.");
        }
        return user;
    }

    @Override
    public void logout() {
        Subject subject = SecurityUtils.getSubject();
        subject.logout();
    }

    @Override
    public Result updatePassword(SysUser sysUser) {
        SysUser user = sysUserMapper.findByUsername(sysUser.getUsername());
        user.setPassword(new SimpleHash("md5", sysUser.getPassword(), ByteSource.Util.bytes(sysUser.getUsername()), 2).toHex());
        if (sysUserMapper.updateById(user) > 0) {
            return Result.success();
        } else {
            return Result.error("Change Password Failure.");
        }
    }

    @Override
    public Result saveNewUser(SysUser sysUser) {
        sysUser.setPassword(new SimpleHash("md5", sysUser.getPassword(), ByteSource.Util.bytes(sysUser.getUsername()), 2).toHex());
        if (sysUserMapper.insert(sysUser) > 0) {
            return Result.success();
        } else {
            return Result.error("Add User Failure");
        }
    }
}
