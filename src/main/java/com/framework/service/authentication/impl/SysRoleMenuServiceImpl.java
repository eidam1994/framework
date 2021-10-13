package com.framework.service.authentication.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.framework.dao.authentication.SysRoleMenuMapper;
import com.framework.entity.authentication.SysRoleMenu;
import com.framework.service.authentication.ISysRoleMenuService;
import org.springframework.stereotype.Service;

/**
 * @description:
 * @author: xingyuzhang
 * @create: 2020-10-21 14:11
 */
@Service
public class SysRoleMenuServiceImpl extends ServiceImpl<SysRoleMenuMapper, SysRoleMenu> implements ISysRoleMenuService {
}
