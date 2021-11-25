package com.framework.service.authentication.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.framework.dao.authentication.SysRoleMenuMapper;
import com.framework.entity.authentication.SysRoleMenu;
import com.framework.service.authentication.ISysRoleMenuService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @description:
 * @author: xingyuzhang
 * @create: 2020-10-21 14:11
 */
@Service
public class SysRoleMenuServiceImpl extends ServiceImpl<SysRoleMenuMapper, SysRoleMenu> implements ISysRoleMenuService {

    @Resource
    private SysRoleMenuMapper roleMenuMapper;

    @Override
    public List<String> selectMenusByRole(String roleId) {
        return roleMenuMapper.selectMenusByRole(roleId);
    }
}
