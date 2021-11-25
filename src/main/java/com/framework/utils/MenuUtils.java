package com.framework.utils;


import com.framework.entity.authentication.SysMenu;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MenuUtils {

    //所有的待用菜单
    public static volatile List<SysMenu> all = null;

    public static List<Map<String, Object>> toTree(List<SysMenu> list) {
        all = new ArrayList<>(list);
        //存放根节点
        ArrayList<Map<String, Object>> roots = new ArrayList<>();
        //添加顶级菜单
        for (SysMenu menu : list) {
            if ("0".equals(menu.getParentId())) {
                roots.add(createMap(menu));
            }
        }
        //删除全局变量中已经被添加节点的数据
        all.removeAll(roots);
        //添加子级菜单
        for (Map<String, Object> root : roots) {
            //一个一个的去找
            root.put("children", getCurrentNodeChildren(root));
        }
        return roots;
    }

    /**
     * 使用递归去查找子项
     *
     * @param root 传入的一个节点
     * @return
     */
    public static List<Map<String, Object>> getCurrentNodeChildren(Map<String, Object> root) {
//        ArrayList<Map<String, Object>> children = new ArrayList<>();
        //判断是否有子节点，有的话就用当前的没有就新建一个空的
        ArrayList<Map<String, Object>> children = root.get("children") == null ? new ArrayList<>() : (ArrayList<Map<String, Object>>) root.get("children");
        for (SysMenu menu : all) {
            if (root.get("id").equals(menu.getParentId())) {
                children.add(createMap(menu));
            }
        }
        //添加完之后删除全局待使用菜单中的数据
        all.removeAll(children);
        //使用递归再次去查询
        //遍历子节点  再去添加孙节点        如果为空的情况就不会去遍历，从而跳出递归
        for (Map<String, Object> child : children) {
            child.put("children", getCurrentNodeChildren(child));
        }
        return children.size() > 0 ? children : null;
    }

    private static Map<String, Object> createMap(SysMenu menu) {
        HashMap<String, Object> map = new HashMap<>();
        map.put("id", menu.getId());
        map.put("path", "/" + menu.getMenuCode());
        map.put("name", menu.getMenuCode());
        map.put("component", menu.getPath());
        HashMap<String, Object> meta = new HashMap<>();
        meta.put("orderNo", menu.getSort());
        meta.put("icon", menu.getIcon());
        meta.put("title", menu.getMenuName());
        map.put("meta", meta);
        return map;
    }

}
