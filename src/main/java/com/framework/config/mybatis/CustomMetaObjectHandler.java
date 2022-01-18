package com.framework.config.mybatis;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import com.framework.utils.UserInfoUtils;
import com.framework.vo.UserInfoVO;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.Objects;

/**
 * @description: 字段自动填充实现
 * @author: xingyuzhang
 * @create: 2020-09-21 09:42
 */
@Component
public class CustomMetaObjectHandler implements MetaObjectHandler {
    
    @Override
    public void insertFill(MetaObject metaObject) {
        this.setFieldValByName("createTime", new Date(), metaObject);
        this.setFieldValByName("updateTime", new Date(), metaObject);
        UserInfoVO userInfo = UserInfoUtils.getUserInfo();
        if (Objects.nonNull(userInfo)) {
            this.setFieldValByName("userId", userInfo.getId(), metaObject);
        }
    }

    @Override
    public void updateFill(MetaObject metaObject) {
        this.setFieldValByName("updateTime", new Date(), metaObject);
    }
}
