package com.framework.entity.base;

import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

@Data
public class Icons {

    /**
     * 主键ID
     */
    @TableId
    private String id;

    /**
     * 图标名
     */
    private String iconName;

    /**
     * 图标来源
     */
    private String source;

}
