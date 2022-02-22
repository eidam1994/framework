package com.framework.controller.base;

import com.framework.constant.response.Result;
import com.framework.entity.base.Icons;
import com.framework.service.base.IIconsService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * 基础类
 * @author ZXY
 */
@RestController
@RequestMapping("/base")
public class BaseController {

    @Resource
    private IIconsService iconsService;

    /**
     * 图标集合
     * @return
     */
    @GetMapping("/icons")
    public Result icons() {
        List<Icons> icons = iconsService.list();
        return Result.success(icons);
    }

}
