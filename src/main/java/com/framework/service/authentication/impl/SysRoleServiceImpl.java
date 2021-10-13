package com.framework.service.authentication.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.framework.dao.authentication.SysRoleMapper;
import com.framework.entity.authentication.SysRole;
import com.framework.service.authentication.ISysRoleService;
import org.springframework.stereotype.Service;

/**
 * @description:
 * @author: xingyuzhang
 * @create: 2020-10-21 14:12
 */
@Service
public class SysRoleServiceImpl extends ServiceImpl<SysRoleMapper, SysRole> implements ISysRoleService {
}
