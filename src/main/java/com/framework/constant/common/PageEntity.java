package com.framework.constant.common;

import lombok.Data;

@Data
public class PageEntity {

    /**
     * 当前页
     */
    private Integer pageNum;

    /**
     * 每页显示数量
     */
    private Integer pageSize;

}
