package com.framework.service.function.disk.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.IdWorker;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.framework.constant.response.Result;
import com.framework.dao.function.disk.WebDiskUploadTaskMapper;
import com.framework.dto.function.UploadDTO;
import com.framework.entity.function.disk.WebDisk;
import com.framework.entity.function.disk.WebDiskUploadTask;
import com.framework.service.function.disk.IWebDiskService;
import com.framework.service.function.disk.IWebDiskUploadTaskService;
import com.framework.utils.UserInfoUtils;
import com.framework.vo.UploadTaskVO;
import com.framework.vo.UserInfoVO;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.FileCopyUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.*;

/**
 * <p>
 * 网盘上传任务表 服务实现类
 * </p>
 *
 * @author Zhang Xingyu
 * @since 2021-12-20
 */
@Service
public class WebDiskUploadTaskServiceImpl extends ServiceImpl<WebDiskUploadTaskMapper, WebDiskUploadTask> implements IWebDiskUploadTaskService {

    @Value("${file.upload.path}")
    private String uploadPath;

    @Resource
    private WebDiskUploadTaskMapper taskMapper;

    @Resource
    private IWebDiskService webDiskService;

    @Override
    public Result taskRegistration(String md5) {
        WebDiskUploadTask task = new WebDiskUploadTask();
        UploadTaskVO taskVO = new UploadTaskVO();
        // 查询上传任务是否已经注册
        QueryWrapper<WebDiskUploadTask> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("md5", md5);
        WebDiskUploadTask registeredTask = taskMapper.selectOne(queryWrapper);
        if (Objects.isNull(registeredTask)) {
            // 查询网盘中是否已经存在文件
            QueryWrapper<WebDisk> diskQueryWrapper = new QueryWrapper<>();
            diskQueryWrapper.eq("md5", md5);
            List<WebDisk> webDisks = webDiskService.list(diskQueryWrapper);
            if (CollectionUtils.isEmpty(webDisks)) {
                task.setId(String.valueOf(IdWorker.getId()));
                task.setMd5(md5);
                taskMapper.insert(task);
                taskVO.setUploadId(task.getId());
                taskVO.setStatus("3");
                taskVO.setPartNumSet(new ArrayList<>());
            } else {
                WebDisk webDisk = webDisks.get(0);
                webDisk.setId(String.valueOf(IdWorker.getId()));
                UserInfoVO userInfo = UserInfoUtils.getUserInfo();
                if (Objects.nonNull(userInfo)) {
                    webDisk.setUserId(userInfo.getId());
                    webDisk.setUploadBy(userInfo.getUsername());
                    webDisk.setCreateTime(new Date());
                    webDisk.setUpdateTime(new Date());
                    webDiskService.save(webDisk);
                    taskVO.setUploadId(task.getId());
                    taskVO.setStatus("4");
                    taskVO.setPartNumSet(new ArrayList<>());
                }
            }
        } else {
            taskVO.setUploadId(registeredTask.getId());
            taskVO.setStatus("2");
            taskVO.setPartNumSet(Arrays.asList(registeredTask.getUploadedIds().split(",")));
        }
        return Result.success(taskVO);
    }

    @Override
    public Result sliceUpload(MultipartFile file, UploadDTO uploadDTO) {
        String uploadId = uploadDTO.getUploadId();
        // 创建分片
        File fileBlob = new File(uploadPath + uploadId + ".blob");
        // 保存分片  后面为true 表示在已有的文件进行追加。
        try {
            FileCopyUtils.copy(file.getInputStream(), new FileOutputStream(fileBlob, true));
        } catch (IOException e) {
            e.printStackTrace();
        }
        WebDiskUploadTask task = taskMapper.selectById(uploadId);
        String uploadedIds = task.getUploadedIds();
        if (StringUtils.isEmpty(uploadedIds)) {
            task.setUploadedIds(uploadDTO.getPartNumber());
        } else {
            List<String> savedIds = new ArrayList<>(Arrays.asList(uploadedIds.split(",")));
            savedIds.add(uploadDTO.getPartNumber());
            task.setUploadedIds(String.join(",", savedIds));
        }
        taskMapper.updateById(task);
        return Result.success("上传切片成功");
    }

    @Override
    public Result mergeFiles(UploadDTO uploadDTO) {
        String suffix = uploadDTO.getFileName().substring(uploadDTO.getFileName().lastIndexOf(".") + 1);
        String uploadId = uploadDTO.getUploadId();
        WebDiskUploadTask uploadTask = taskMapper.selectById(uploadDTO.getUploadId());
        File fileBlob = new File(uploadPath + uploadId + ".blob");
        File saveFile = new File(uploadPath + uploadId + "." + suffix);
        //重命名操作
        fileBlob.renameTo(saveFile);
        WebDisk webDisk = new WebDisk();
        webDisk.setFileName(uploadDTO.getFileName());
        webDisk.setFileSize(uploadDTO.getSize());
        webDisk.setStoragePath(uploadPath + uploadId + "." + suffix);
        UserInfoVO userInfo = UserInfoUtils.getUserInfo();
        if (Objects.nonNull(userInfo)) {
            webDisk.setUserId(userInfo.getId());
            webDisk.setUploadBy(userInfo.getUsername());
        }
        webDisk.setMd5(uploadTask.getMd5());
        webDisk.setCreateTime(new Date());
        webDisk.setUpdateTime(new Date());
        webDiskService.save(webDisk);
        taskMapper.deleteById(uploadId);
        return Result.success("合并成功");
    }
}
