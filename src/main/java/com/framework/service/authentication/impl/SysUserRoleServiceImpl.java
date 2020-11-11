package com.framework.service.authentication.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.framework.dao.authentication.SysUserRoleMapper;
import com.framework.entity.authentication.SysUserRole;
import com.framework.service.authentication.ISysUserRoleService;
import org.springframework.stereotype.Service;

/**
 * @description:
 * @author: xingyuzhang
 * @create: 2020-10-21 14:13
 */
@Service
public class SysUserRoleServiceImpl extends ServiceImpl<SysUserRoleMapper, SysUserRole> implements ISysUserRoleService {
}
