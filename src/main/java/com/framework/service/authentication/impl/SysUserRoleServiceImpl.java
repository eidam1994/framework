package com.framework.service.authentication.impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.framework.constant.response.Result;
import com.framework.dao.authentication.SysUserRoleMapper;
import com.framework.entity.authentication.SysUserRole;
import com.framework.service.authentication.ISysUserRoleService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @description:
 * @author: xingyuzhang
 * @create: 2020-10-21 14:13
 */
@Service
public class SysUserRoleServiceImpl extends ServiceImpl<SysUserRoleMapper, SysUserRole> implements ISysUserRoleService {
    
    @Resource
    private SysUserRoleMapper userRoleMapper;
    
    @Override
    public List<String> selectUsersByRoleId(String roleId) {
        return userRoleMapper.selectUsersByRoleId(roleId);
    }

    @Override
    public Result updateUsersOfRole(String roleId, String userIds) {
        String[] users = userIds.split(",");
        QueryWrapper<SysUserRole> wrapper = new QueryWrapper<>();
        wrapper.eq("role_id", roleId);
        userRoleMapper.delete(wrapper);
        if (StringUtils.isNotEmpty(userIds)) {
            for (String user : users) {
                SysUserRole newUserRole = new SysUserRole();
                newUserRole.setRoleId(Long.valueOf(roleId));
                newUserRole.setUserId(Long.valueOf(user));
                userRoleMapper.insert(newUserRole);
            }   
        }
        return Result.success();
    }
}
