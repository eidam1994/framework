package com.framework.controller.function;

import com.framework.constant.response.Result;
import com.framework.pageDto.function.SearchPageDTO;
import com.framework.service.function.IWebDiskService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/webDisk")
public class WebDiskController {

    @Resource
    private IWebDiskService webDiskService;

    /**
     * 查询网盘列表
     * @param pageDTO
     * @return
     */
    @PostMapping("/list")
    public Result list(@RequestBody SearchPageDTO pageDTO) {
        return webDiskService.getWebDiskPage(pageDTO);
    }

}
