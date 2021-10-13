package com.framework.service.authentication.impl;

import cn.dev33.satoken.stp.StpUtil;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.framework.constant.exception.CustomExceptionType;
import com.framework.constant.response.Result;
import com.framework.dao.authentication.SysUserMapper;
import com.framework.entity.authentication.SysUser;
import com.framework.exception.CustomException;
import com.framework.service.authentication.ISysUserService;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

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
    public Result doLogin(String username, String password) {
        SysUser sysUser = findByUsername(username);
        if (sysUser != null) {
            String loginPassword = DigestUtils.md5DigestAsHex((username + password).getBytes());
            if (loginPassword.equals(sysUser.getPassword())) {
                StpUtil.login(sysUser.getId());
                return Result.success();
            } else {
                return Result.error("密码错误");
            }
        } else {
            return Result.error("账号不存在");
        }
    }

    @Override
    public SysUser findByUsername(String username) {
        return sysUserMapper.findByUsername(username);
    }

    @Override
    public Result updatePassword(SysUser sysUser) {
        SysUser user = sysUserMapper.findByUsername(sysUser.getUsername());
        String md5Password = DigestUtils.md5DigestAsHex((sysUser.getUsername() + sysUser.getPassword()).getBytes());
        sysUser.setPassword(md5Password);
        if (sysUserMapper.updateById(user) > 0) {
            return Result.success();
        } else {
            return Result.error("Change Password Failure.");
        }
    }

    @Override
    public Result saveNewUser(SysUser sysUser) {
        String md5Password = DigestUtils.md5DigestAsHex((sysUser.getUsername() + sysUser.getPassword()).getBytes());
        sysUser.setPassword(md5Password);
        if (sysUserMapper.insert(sysUser) > 0) {
            return Result.success();
        } else {
            return Result.error("Add User Failure");
        }
    }
}
