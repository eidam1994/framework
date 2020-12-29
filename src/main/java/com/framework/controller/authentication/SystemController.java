package com.framework.controller.authentication;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.framework.constant.response.Result;
import com.framework.entity.authentication.SysUser;
import com.framework.entity.movie.Movie;
import com.framework.service.authentication.ISysUserService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * @description: 系统管理
 * @author: xingyuzhang
 * @create: 2020-12-29 16:12
 */
@RestController
public class SystemController {
    
    @Resource
    private ISysUserService userService;

    @GetMapping("/v1/users")
    public Result getAllUsers() {
        List<SysUser> userList = userService.list();
        return Result.success(userList);
    }
    
}
