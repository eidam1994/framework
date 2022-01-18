package com.framework.entity.function.note;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 记事本
 */
@Data
public class Notepad {

    /**
     * 主键ID
     */
    @TableId
    private String id;

    /**
     * 标题
     */
    private String title;

    /**
     * 记录内容
     */
    private String content;

    /**
     * 标签
     */
    private String tags;

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

    /**
     * 所属人ID
     */
    @TableField(fill = FieldFill.INSERT)
    private String userId;

}
