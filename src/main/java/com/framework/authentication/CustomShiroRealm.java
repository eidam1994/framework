package com.framework.authentication;

import com.framework.entity.authentication.SysUser;
import com.framework.service.authentication.ISysMenuService;
import com.framework.service.authentication.ISysRoleService;
import com.framework.service.authentication.ISysUserService;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;

import javax.annotation.Resource;
import java.util.Objects;

/**
 * @description: 自定义 shiroRealm, 主要是重写其认证、授权
 * @author: xingyuzhang
 * @create: 2020-10-21 11:33
 */
@Slf4j
public class CustomShiroRealm extends AuthorizingRealm {

    @Resource
    private ISysUserService userService;
    
    @Resource
    private ISysRoleService roleService;

    @Resource
    private ISysMenuService menuService;
    
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        log.info("开始执行授权操作.......");
//        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
//        //如果身份认证的时候没有传入User对象，这里只能取到userName
//        //也就是SimpleAuthenticationInfo构造的时候第一个参数传递需要User对象
//        SysUser user = (SysUser) principals.getPrimaryPrincipal();

//        // 查询用户角色，一个用户可能有多个角色
//        List<Role> roles = iRoleService.getUserRoles(user.getUserId());
//
//        for (Role role : roles) {
//            authorizationInfo.addRole(role.getRole());
//            // 根据角色查询权限
//            List<Permission> permissions = iPermissionService.getRolePermissions(role.getRoleId());
//            for (Permission p : permissions) {
//                authorizationInfo.addStringPermission(p.getPermission());
//            }
//        }
//        return authorizationInfo;
        return null;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        log.info("开始进行身份认证......");
        // 获取用户的输入的账号.
        String userName = (String) token.getPrincipal();
        // 通过username从数据库中查找 User对象.
        SysUser user = userService.findByUsername(userName);
        if (user == null) {
            throw new UnknownAccountException();
        }
        return new SimpleAuthenticationInfo(
                // 这里传入的是user对象，比对的是用户名，直接传入用户名也没错，但是在授权部分就需要自己重新从数据库里取权限
                user,
                // 密码
                user.getPassword(),
                // salt = username
                ByteSource.Util.bytes(user.getUsername()),
                // realm name
                getName()
        );
    }
}
