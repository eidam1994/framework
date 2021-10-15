package com.framework.pageDto;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.framework.entity.authentication.SysUser;
import lombok.Data;
import org.springframework.web.bind.annotation.RequestBody;

@Data
public class UserPageDTO extends Page<SysUser> {

    private String loginName;

    private String username;

    private String phoneNumber;

}
