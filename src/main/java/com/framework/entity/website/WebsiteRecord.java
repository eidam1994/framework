package com.framework.entity.website;

import com.baomidou.mybatisplus.annotation.TableId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @description: 网站收藏记录
 * @author: xingyuzhang
 * @create: 2020-11-20 10:45
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class WebsiteRecord implements Serializable {

    /**
     * 网站id
     */
    @TableId
    private Long id;

    /**
     * 网站名
     */
    private String websiteName;

    /**
     * 网站地址
     */
    private String websiteUrl;

    /**
     * 网站图标
     */
    private String websiteIcon;

    /**
     * 打开方式 _self _blank
     */
    private String openTarget;

    /**
     * 类型
     */
    private String websiteType;

    private static final long serialVersionUID = 1L;
    
}
