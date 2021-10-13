package com.framework.service.authentication.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.framework.dao.authentication.SysMenuMapper;
import com.framework.entity.authentication.SysMenu;
import com.framework.service.authentication.ISysMenuService;
import org.springframework.stereotype.Service;

/**
 * @description:
 * @author: xingyuzhang
 * @create: 2020-10-21 14:00
 */
@Service
public class SysMenuServiceImpl extends ServiceImpl<SysMenuMapper, SysMenu> implements ISysMenuService {
}
