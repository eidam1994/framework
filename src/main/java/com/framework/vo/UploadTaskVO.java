package com.framework.vo;

import lombok.Data;

import java.util.List;

/**
 * 网盘上传任务VO
 */
@Data
public class UploadTaskVO {

    /**
     * 上传ID
     */
    private String uploadId;

    /**
     * 传状态 1-已全部上传，未合并 2-已上传部分 3-未上传过
     */
    private String status;

    /**
     * 已上传切片
     */
    private List<String> partNumSet;

}
