package com.framework.controller.authentication;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.framework.constant.response.Result;
import com.framework.entity.authentication.SysRole;
import com.framework.pageDto.authentication.RolePageDTO;
import com.framework.service.authentication.ISysRoleService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/role")
public class SysRoleController {

    @Autowired
    private ISysRoleService roleService;

    /**
     * 角色列表查询
     * @param rolePageDTO
     * @return
     */
    @PostMapping("/list")
    public Result list(@RequestBody RolePageDTO rolePageDTO) {
        QueryWrapper<SysRole> roleQueryWrapper = new QueryWrapper<>();
        if (StringUtils.isNotEmpty(rolePageDTO.getRoleName())) {
            roleQueryWrapper.like("role_name", rolePageDTO.getRoleName());
        }
        return Result.success(roleService.page(rolePageDTO, roleQueryWrapper));
    }

    @PostMapping("/save")
    public Result save(@RequestBody SysRole role) {
        roleService.saveOrUpdate(role);
        return Result.success();
    }

    @DeleteMapping("/delete/{id}")
    public Result delete(@PathVariable("id") String id) {
        roleService.removeById(id);
        return Result.success();
    }
}
