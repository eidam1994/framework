package com.framework.service.authentication.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.framework.constant.response.Result;
import com.framework.dao.authentication.SysMenuMapper;
import com.framework.entity.authentication.SysMenu;
import com.framework.service.authentication.ISysMenuService;
import com.framework.utils.MenuUtils;
import com.framework.utils.UserInfoUtils;
import com.framework.vo.UserInfoVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @description:
 * @author: xingyuzhang
 * @create: 2020-10-21 14:00
 */
@Service
public class SysMenuServiceImpl extends ServiceImpl<SysMenuMapper, SysMenu> implements ISysMenuService {

    @Resource
    private SysMenuMapper menuMapper;

    @Override
    public Result getMenuList() {
        UserInfoVO userInfo = UserInfoUtils.getUserInfo();
        if (userInfo != null) {
            List<SysMenu> menus = menuMapper.selectMenuList(String.valueOf(userInfo.getId()));
            List<SysMenu> completeMenus = createCompleteMenus(menus);
            List<Map<String, Object>> menuTree = MenuUtils.toTree(completeMenus);
            return Result.success(menuTree);
        }
        return Result.error("用户未登录");
    }

    @Override
    public List<SysMenu> createCompleteMenus(List<SysMenu> menus) {
        Set<String> parentIds = new HashSet<>();
        for (SysMenu menu : menus) {
            if (!"0".equals(menu.getParentId()) && !parentIds.contains(menu.getParentId()) && menus.stream().noneMatch(w->w.getId().equals(menu.getParentId()))) {
                parentIds.add(menu.getParentId());
            }
        }
        if (CollectionUtils.isEmpty(parentIds)) {
            return menus;
        } else {
            for (String parentId : parentIds) {
                SysMenu menu = this.getById(parentId);
                if (menu != null) {
                    menus.add(menu);
                }
            }
            return createCompleteMenus(menus);
        }
    }
}
