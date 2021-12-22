package com.framework.service.function.disk.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.framework.constant.response.Result;
import com.framework.dao.function.disk.WebDiskMapper;
import com.framework.entity.function.disk.WebDisk;
import com.framework.dto.function.SearchPageDTO;
import com.framework.service.function.disk.IWebDiskService;
import com.framework.utils.UserInfoUtils;
import com.framework.vo.UserInfoVO;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class WebDiskServiceImpl extends ServiceImpl<WebDiskMapper, WebDisk> implements IWebDiskService {

    @Resource
    private WebDiskMapper webDiskMapper;

    @Override
    public Result getWebDiskPage(SearchPageDTO pageDTO) {
        UserInfoVO userInfo = UserInfoUtils.getUserInfo();
        if (userInfo != null) {
            pageDTO.setUserId(userInfo.getId());
        }
        PageHelper.startPage(pageDTO.getPageNum(), pageDTO.getPageSize());
        List<WebDisk> webDisks = webDiskMapper.selectWebDiskList(pageDTO);
        PageInfo<WebDisk> pageInfo = new PageInfo<>(webDisks);
        return Result.success(pageInfo);
    }
}
