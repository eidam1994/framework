package com.framework.dto.function;

import lombok.Data;

import java.util.List;

@Data
public class NotepadDTO {

    /**
     * 笔记id
     */
    private String id;

    /**
     * 标题
     */
    private String title;

    /**
     * 笔记内容
     */
    private String content;

    /**
     * 标签
     */
    private List<String> tags;

    /**
     * 搜索标签
     */
    private String searchTags;

    /**
     * 用户编码
     */
    private String userId;

}
