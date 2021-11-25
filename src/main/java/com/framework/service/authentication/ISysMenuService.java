package com.framework.service.authentication;

import com.baomidou.mybatisplus.extension.service.IService;
import com.framework.constant.response.Result;
import com.framework.entity.authentication.SysMenu;

import java.awt.*;
import java.util.List;

/**
 * @description:
 * @author: xingyuzhang
 * @create: 2020-10-21 13:58
 */
public interface ISysMenuService extends IService<SysMenu> {

    /**
     * 加载菜单
     * @return
     */
    Result getMenuList();

    /**
     * 生成完整的菜单数据
     * @param menus
     * @return
     */
    List<SysMenu> createCompleteMenus(List<SysMenu> menus);

}
