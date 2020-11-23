package com.framework.service.website.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.framework.dao.website.WebsiteRecordMapper;
import com.framework.entity.website.WebsiteRecord;
import com.framework.service.website.IWebsiteRecordService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @description: 网站记录业务实现
 * @author: xingyuzhang
 * @create: 2020-11-20 11:00
 */
@Service
public class WebsiteRecordServiceImpl extends ServiceImpl<WebsiteRecordMapper, WebsiteRecord> implements IWebsiteRecordService {
    @Override
    public List<WebsiteRecord> selectAllRecords() {
        return this.list();
    }

    @Override
    public Boolean saveWebsiteRecord(WebsiteRecord websiteRecord) {
        return this.save(websiteRecord);
    }

    @Override
    public Boolean deleteWebsiteRecord(String id) {
        return this.removeById(id);
    }
}
