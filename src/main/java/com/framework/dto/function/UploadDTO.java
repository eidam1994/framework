package com.framework.dto.function;

import lombok.Data;

@Data
public class UploadDTO {

    /**
     * 上传文件md5码
     */
    private String md5Code;

    /**
     * 上传任务ID
     */
    private String uploadId;

    /**
     * 上传切片编号
     */
    private String partNumber;

    /**
     * 文件名
     */
    private String fileName;

    /**
     * 文件大小
     */
    private String size;

}
