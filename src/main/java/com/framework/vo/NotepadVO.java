package com.framework.vo;

import lombok.Data;

import java.util.List;

@Data
public class NotepadVO {

    /**
     * 笔记ID
     */
    private String id;

    /**
     * 标题
     */
    private String title;

    /**
     * 预览内容
     */
    private String content;

    /**
     * 创作时间
     */
    private String updateTime;

    /**
     * 标签集合
     */
    private List<String> tags;

}
