package com.framework.service.authentication.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.framework.constant.response.Result;
import com.framework.dao.authentication.SysRoleMapper;
import com.framework.entity.authentication.SysRole;
import com.framework.dto.authentication.RolePageDTO;
import com.framework.service.authentication.ISysRoleMenuService;
import com.framework.service.authentication.ISysRoleService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @description:
 * @author: xingyuzhang
 * @create: 2020-10-21 14:12
 */
@Service
public class SysRoleServiceImpl extends ServiceImpl<SysRoleMapper, SysRole> implements ISysRoleService {

    @Resource
    private SysRoleMapper roleMapper;

    @Resource
    private ISysRoleMenuService roleMenuService;

    @Override
    public Result getRolePageList(RolePageDTO pageDTO) {
        PageHelper.startPage(pageDTO.getPageNum(), pageDTO.getPageSize());
        List<SysRole> roles = roleMapper.selectRolePage(pageDTO);
        for (SysRole role : roles) {
            List<String> menus = roleMenuService.selectMenusByRole(role.getId());
            role.setMenuIds(menus);
        }
        PageInfo<SysRole> rolePageInfo = new PageInfo<>(roles);
        return Result.success(rolePageInfo);
    }
}
