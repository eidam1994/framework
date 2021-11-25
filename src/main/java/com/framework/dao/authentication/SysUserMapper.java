package com.framework.dao.authentication;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.framework.entity.authentication.SysUser;
import com.framework.pageDto.authentication.UserPageDTO;

import java.util.List;

/**
 * @author Admin
 */
public interface SysUserMapper extends BaseMapper<SysUser> {

    /**
     * 根据用户名查询用户信息
     * @param loginName
     * @return
     */
    SysUser findByLoginName(String loginName);

    /**
     * 用户列表查询
     * @param pageDTO
     * @return
     */
    List<SysUser> selectUserList(UserPageDTO pageDTO);
}