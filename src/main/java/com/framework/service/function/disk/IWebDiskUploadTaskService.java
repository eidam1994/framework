package com.framework.service.function.disk;

import com.baomidou.mybatisplus.extension.service.IService;
import com.framework.constant.response.Result;
import com.framework.dto.function.UploadDTO;
import com.framework.entity.function.disk.WebDiskUploadTask;
import org.springframework.web.multipart.MultipartFile;

/**
 * <p>
 * 网盘上传任务表 服务类
 * </p>
 *
 * @author Zhang Xingyu
 * @since 2021-12-20
 */
public interface IWebDiskUploadTaskService extends IService<WebDiskUploadTask> {

    /**
     * 注册网盘上传任务
     * @param md5
     * @return
     */
    Result taskRegistration(String md5);

    /**
     * 上传保存切片
     * @param file
     * @param uploadDTO
     * @return
     */
    Result sliceUpload(MultipartFile file, UploadDTO uploadDTO);

    /**
     * 完成上传，合并文件分片
     * @param uploadDTO
     * @return
     */
    Result mergeFiles(UploadDTO uploadDTO);

}
