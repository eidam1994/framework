package com.framework.entity.function.disk;

import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.util.Date;
import java.util.List;

/**
 * 网盘上传任务
 */
@Data
public class WebDiskUploadTask {

    /**
     * 上传任务ID
     */
    @TableId
    private String id;

    /**
     * 已上传切片id集合
     */
    private String uploadedIds;

    /**
     * 文件md5码
     */
    private String md5;

}
