package com.framework.service.function;

import com.baomidou.mybatisplus.extension.service.IService;
import com.framework.constant.response.Result;
import com.framework.entity.function.WebDisk;
import com.framework.pageDto.function.SearchPageDTO;

public interface IWebDiskService extends IService<WebDisk> {

    /**
     * 获取网盘数据列表
     * @param pageDTO
     * @return
     */
    Result getWebDiskPage(SearchPageDTO pageDTO);

}
