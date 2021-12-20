package com.framework.entity.function;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class WebDisk implements Serializable {

    /**
     * 主键ID
     */
    @TableId
    private String id;

    /**
     * 文件名
     */
    private String fileName;

    /**
     * 文件大小
     */
    private String fileSize;

    /**
     * 存储路径
     */
    private String storagePath;

    /**
     * 备注
     */
    private String remark;

    /**
     * 所属用户
     */
    private String userId;

    /**
     * 上传人
     */
    private String uploadBy;

    /**
     * 创建时间(上传时间)
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @TableField(fill = FieldFill.INSERT)
    private Date createTime;

    /**
     * 修改时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date updateTime;

}
