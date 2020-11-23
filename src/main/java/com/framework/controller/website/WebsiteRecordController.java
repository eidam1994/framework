package com.framework.controller.website;

import com.framework.constant.response.Result;
import com.framework.entity.website.WebsiteRecord;
import com.framework.service.website.IWebsiteRecordService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * @description: 网站记录视图
 * @author: xingyuzhang
 * @create: 2020-11-20 11:13
 */
@RestController
public class WebsiteRecordController {
    
    @Resource
    private IWebsiteRecordService websiteRecordService;

    @GetMapping("/public/v1/websites")
    public Result getAllWebsites() {
        List<WebsiteRecord> websiteRecords = websiteRecordService.selectAllRecords();
        return Result.success(websiteRecords);
    }
    
    @PostMapping("/public/v1/websites")
    public Result saveWebsite(WebsiteRecord websiteRecord) {
        Boolean isSuccess = websiteRecordService.saveWebsiteRecord(websiteRecord);
        if (isSuccess) {
            return Result.success();
        } else {
            return Result.error("保存失败");
        }
    }
    
    @DeleteMapping("/public/v1/{id}/websites")
    public Result deleteWebsite(@PathVariable String id) {
        Boolean isSuccess = websiteRecordService.deleteWebsiteRecord(id);
        if (isSuccess) {
            return Result.success();
        } else {
            return Result.error("删除失败");
        }
    }
}
