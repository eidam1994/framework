package com.framework.pageDto.function;

import com.framework.constant.common.PageEntity;
import lombok.Data;

/**
 * 搜索传输类
 */
@Data
public class SearchPageDTO extends PageEntity {

    /**
     * 搜索内容，针对一般搜索情况
     */
    private String searchContent;

    /**
     * 用户编号
     */
    private String userId;

}
