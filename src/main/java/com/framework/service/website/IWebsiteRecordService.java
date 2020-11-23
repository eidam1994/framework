package com.framework.service.website;

import com.baomidou.mybatisplus.extension.service.IService;
import com.framework.entity.website.WebsiteRecord;

import java.util.List;

/**
 * @description: 网站记录业务层
 * @author: xingyuzhang
 * @create: 2020-11-20 10:58
 */
public interface IWebsiteRecordService extends IService<WebsiteRecord> {

    /**
     * 查询所有的网站记录
     * @return
     */
    List<WebsiteRecord> selectAllRecords();

    /**
     * 保存网站记录
     * @param websiteRecord
     * @return
     */
    Boolean saveWebsiteRecord(WebsiteRecord websiteRecord);

    /**
     * 删除网站记录
     * @param id
     * @return
     */
    Boolean deleteWebsiteRecord(String id);
    
}
