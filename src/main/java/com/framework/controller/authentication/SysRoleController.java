package com.framework.controller.authentication;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.framework.constant.response.Result;
import com.framework.entity.authentication.SysRole;
import com.framework.entity.authentication.SysRoleMenu;
import com.framework.dto.authentication.RolePageDTO;
import com.framework.service.authentication.ISysRoleMenuService;
import com.framework.service.authentication.ISysRoleService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/role")
public class SysRoleController {

    @Resource
    private ISysRoleService roleService;

    @Resource
    private ISysRoleMenuService roleMenuService;

    /**
     * 角色列表查询
     * @param rolePageDTO
     * @return
     */
    @PostMapping("/list")
    public Result list(@RequestBody RolePageDTO rolePageDTO) {
        return roleService.getRolePageList(rolePageDTO);
    }

    @PostMapping("/save")
    public Result save(@RequestBody SysRole role) {
        roleService.saveOrUpdate(role);
        List<String> menuIds = role.getMenuIds();
        QueryWrapper<SysRoleMenu> removeWrapper = new QueryWrapper<>();
        removeWrapper.eq("role_id", role.getId());
        roleMenuService.remove(removeWrapper);
        for (String menuId : menuIds) {
            SysRoleMenu roleMenu = new SysRoleMenu();
            roleMenu.setRoleId(role.getId());
            roleMenu.setMenuId(menuId);
            roleMenuService.save(roleMenu);
        }
        return Result.success();
    }

    @DeleteMapping("/delete/{id}")
    public Result delete(@PathVariable("id") String id) {
        roleService.removeById(id);
        return Result.success();
    }

    @GetMapping("/roleList")
    public Result roleList() {
        List<SysRole> list = roleService.list();
        return Result.success(list);
    }
}
