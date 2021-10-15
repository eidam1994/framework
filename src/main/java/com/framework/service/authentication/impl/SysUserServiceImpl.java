package com.framework.service.authentication.impl;

import cn.dev33.satoken.stp.StpUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.framework.constant.exception.CustomExceptionType;
import com.framework.constant.response.Result;
import com.framework.dao.authentication.SysUserMapper;
import com.framework.entity.authentication.SysUser;
import com.framework.exception.CustomException;
import com.framework.service.authentication.ISysUserService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;
import org.springframework.util.ObjectUtils;

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
        return sysUserMapper.findByLoginName(username);
    }

    @Override
    public Result updatePassword(SysUser sysUser) {
        SysUser user = sysUserMapper.findByLoginName(sysUser.getLoginName());
        String md5Password = DigestUtils.md5DigestAsHex((sysUser.getLoginName() + sysUser.getPassword()).getBytes());
        sysUser.setPassword(md5Password);
        if (sysUserMapper.updateById(user) > 0) {
            return Result.success();
        } else {
            return Result.error("修改密码失败");
        }
    }

    @Override
    public Result saveUser(SysUser sysUser) {
        Long id = sysUser.getId();
        // 判断登录名是否重复
        QueryWrapper<SysUser> idWrapper = new QueryWrapper<>();
        idWrapper.eq("login_name", sysUser.getLoginName());
        if (!ObjectUtils.isEmpty(id)) {
            idWrapper.ne("id", id);
        }
        SysUser user = sysUserMapper.selectOne(idWrapper);
        if (user != null) {
            return Result.error("登录名已存在");
        }
        String md5Password = DigestUtils.md5DigestAsHex((sysUser.getLoginName() + sysUser.getPassword()).getBytes());
        if (!ObjectUtils.isEmpty(id)) {
            SysUser userDB = sysUserMapper.selectById(id);
            if (!md5Password.equals(userDB.getPassword())) {
                sysUser.setPassword(md5Password);
            }
        } else {
            sysUser.setPassword(md5Password);
        }
        if (saveOrUpdate(sysUser)) {
            return Result.success();
        } else {
            return Result.error("新增用户失败");
        }
    }
}
