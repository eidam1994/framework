package com.framework.dao.authentication;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.framework.entity.authentication.SysMenu;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Admin
 */
@Repository
public interface SysMenuMapper extends BaseMapper<SysMenu> {

    List<SysMenu> selectMenuList(String userId);
    
}