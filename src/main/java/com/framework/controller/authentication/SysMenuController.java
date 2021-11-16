package com.framework.controller.authentication;

import com.framework.constant.response.Result;
import com.framework.entity.authentication.SysMenu;
import com.framework.service.authentication.ISysMenuService;
import com.framework.utils.TreeUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.List;

@RestController
@RequestMapping("/menu")
public class SysMenuController {

    @Autowired
    private ISysMenuService menuService;

    /**
     * 菜单列表加载
     * @return
     */
    @GetMapping("/list")
    public Result list() {
        List<SysMenu> menus = menuService.list();
        Collection<SysMenu> sysMenus = TreeUtils.toTree(menus, "id", "parentId", "children", SysMenu.class);
        return Result.success(sysMenus);
    }

    /**
     * 保存菜单数据
     * @param menu
     * @return
     */
    @PostMapping("/save")
    public Result save(@RequestBody SysMenu menu) {
        menuService.saveOrUpdate(menu);
        return Result.success();
    }

}
