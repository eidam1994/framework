package com.framework.controller.function.disk;

import com.framework.constant.response.Result;
import com.framework.dto.function.UploadDTO;
import com.framework.entity.function.disk.WebDiskUploadTask;
import com.framework.service.function.disk.IWebDiskUploadTaskService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

@RestController
@RequestMapping("/task")
public class WebDiskUploadTaskController {

    @Value("${file.upload.path}")
    private String uploadPath;

    @Resource
    private IWebDiskUploadTaskService taskService;

    /**
     * 注册或查找已注册网盘上传任务
     *
     * @param md5
     * @return
     */
    @GetMapping("/register")
    public Result taskRegistration(@RequestParam String md5) {
        return taskService.taskRegistration(md5);
    }

    /**
     * 分片上传文件
     *
     * @param file
     */
    @PostMapping("/upload")
    public Result sliceUpload(MultipartFile file, UploadDTO uploadDTO) {
        return taskService.sliceUpload(file, uploadDTO);
    }

    /**
     * 合并已上传文件
     * @param uploadDTO
     * @return
     */
    @PostMapping("/merge")
    public Result mergeFiles(@RequestBody UploadDTO uploadDTO) {
        return taskService.mergeFiles(uploadDTO);
    }

    /**
     * 停止上传文件
     * @param id
     * @return
     */
    @DeleteMapping("/cancel/{id}")
    public Result cancelUpload(@PathVariable("id") String id) {
        taskService.removeById(id);
        File file = new File(uploadPath + id + ".blob");
        file.delete();
        return Result.success("删除成功");
    }

}
