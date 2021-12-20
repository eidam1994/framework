package com.framework.dao.function;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.framework.entity.function.WebDisk;
import com.framework.pageDto.function.SearchPageDTO;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WebDiskMapper extends BaseMapper<WebDisk> {

    /**
     * 查询网盘信息列表
     * @param pageDTO
     * @return
     */
    List<WebDisk> selectWebDiskList(SearchPageDTO pageDTO);

}
