package com.framework.service.authentication.impl;

import cn.dev33.satoken.stp.StpUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.framework.constant.exception.CustomExceptionType;
import com.framework.constant.response.Result;
import com.framework.dao.authentication.SysUserMapper;
import com.framework.entity.authentication.SysUser;
import com.framework.entity.authentication.SysUserRole;
import com.framework.exception.CustomException;
import com.framework.pageDto.authentication.UserPageDTO;
import com.framework.service.authentication.ISysRoleService;
import com.framework.service.authentication.ISysUserRoleService;
import com.framework.service.authentication.ISysUserService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;
import org.springframework.util.ObjectUtils;

import javax.annotation.Resource;
import java.util.List;

/**
 * @description:
 * @author: xingyuzhang
 * @create: 2020-10-21 14:12
 */
@Service
public class SysUserServiceImpl extends ServiceImpl<SysUserMapper, SysUser> implements ISysUserService {
    
    @Resource
    private SysUserMapper sysUserMapper;

    @Resource
    private ISysUserRoleService userRoleService;

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
    public Result userList(UserPageDTO pageDTO) {
//        PageHelper.startPage(pageDTO.getPageNum(), pageDTO.getPageSize());
        List<SysUser> users = sysUserMapper.selectUserList(pageDTO);
        for (SysUser user : users) {
            List<String> roleIds = userRoleService.selectRolesByUserId(user.getId());
            user.setRoleIds(roleIds);
        }
//        PageInfo<SysUser> userPageInfo = new PageInfo<>(users);
        return Result.success(users);
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
        String id = sysUser.getId();
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
            if (!sysUser.getPassword().equals(userDB.getPassword())) {
                sysUser.setPassword(md5Password);
            }
        } else {
            sysUser.setPassword(md5Password);
        }
        if (saveOrUpdate(sysUser)) {
            List<String> roleIds = sysUser.getRoleIds();
            QueryWrapper<SysUserRole> removeWrapper = new QueryWrapper<>();
            removeWrapper.eq("USER_ID", sysUser.getId());
            userRoleService.remove(removeWrapper);
            for (String roleId : roleIds) {
                SysUserRole userRole = new SysUserRole();
                userRole.setUserId(sysUser.getId());
                userRole.setRoleId(roleId);
                userRoleService.save(userRole);
            }
            return Result.success();
        } else {
            return Result.error("新增用户失败");
        }
    }

    @Override
    public Result register(SysUser sysUser) {
        // 判断登录名是否重复
        QueryWrapper<SysUser> idWrapper = new QueryWrapper<>();
        idWrapper.eq("login_name", sysUser.getLoginName());
        SysUser user = sysUserMapper.selectOne(idWrapper);
        if (user != null) {
            return Result.error("登录名已存在");
        }
        String md5Password = DigestUtils.md5DigestAsHex((sysUser.getLoginName() + sysUser.getPassword()).getBytes());
        sysUser.setPassword(md5Password);
        if (save(sysUser)) {
            return Result.success();
        } else {
            return Result.error("注册失败");
        }
    }
}
