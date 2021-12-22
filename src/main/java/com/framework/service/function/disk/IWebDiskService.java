package com.framework.service.function.disk;

import com.baomidou.mybatisplus.extension.service.IService;
import com.framework.constant.response.Result;
import com.framework.entity.function.disk.WebDisk;
import com.framework.dto.function.SearchPageDTO;

public interface IWebDiskService extends IService<WebDisk> {

    /**
     * 获取网盘数据列表
     * @param pageDTO
     * @return
     */
    Result getWebDiskPage(SearchPageDTO pageDTO);

}
