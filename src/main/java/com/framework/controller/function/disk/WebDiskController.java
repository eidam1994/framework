package com.framework.controller.function.disk;

import com.framework.constant.response.Result;
import com.framework.dto.function.SearchPageDTO;
import com.framework.entity.function.disk.WebDisk;
import com.framework.service.function.disk.IWebDiskService;
import com.framework.utils.UserInfoUtils;
import com.framework.vo.UserInfoVO;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.*;

@RestController
@RequestMapping("/webDisk")
public class WebDiskController {

    @Resource
    private IWebDiskService webDiskService;

    /**
     * 查询网盘列表
     *
     * @param pageDTO
     * @return
     */
    @PostMapping("/list")
    public Result list(@RequestBody SearchPageDTO pageDTO) {
        return webDiskService.getWebDiskPage(pageDTO);
    }

    /**
     * 删除网盘数据
     *
     * @param id
     * @return
     */
    @DeleteMapping("/delete/{id}")
    public Result delete(@PathVariable("id") String id) {
        WebDisk webDisk = webDiskService.getById(id);
        webDiskService.removeById(id);
        File file = new File(webDisk.getStoragePath());
        file.delete();
        return Result.success("删除成功");
    }

    /**
     * 更新网盘文件信息
     *
     * @param webDisk
     * @return
     */
    @PostMapping("/update")
    public Result update(@RequestBody WebDisk webDisk, HttpServletResponse response) {
        String userId = webDisk.getUserId();
        if ("1".equals(userId)) {
            UserInfoVO userInfo = UserInfoUtils.getUserInfo();
            if (userInfo != null) {
                webDisk.setUserId(userInfo.getId());
            }
        } else {
            webDisk.setUserId(null);
        }
        webDiskService.updateById(webDisk);
        return Result.success("修改成功");
    }

    /**
     * 下载网盘中的文件
     *
     * @param id
     */
    @GetMapping("/download")
    public void download(@RequestParam String id, HttpServletResponse response) {
        try {
            WebDisk webDisk = webDiskService.getById(id);
            File file = new File(webDisk.getStoragePath());
            // 取得文件名。
            String filename = webDisk.getFileName();
            // 以流的形式下载文件。
            InputStream fis = new BufferedInputStream(new FileInputStream(webDisk.getStoragePath()));
            byte[] buffer = new byte[fis.available()];
            fis.read(buffer);
            fis.close();
            // 清空response
            response.reset();
            // 设置response的Header
            response.addHeader("Content-Disposition", "attachment;filename=" + filename);
            response.addHeader("Content-Length", "" + file.length());
            OutputStream toClient = new BufferedOutputStream(response.getOutputStream());
            response.setContentType("application/octet-stream");
            toClient.write(buffer);
            toClient.flush();
            toClient.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

}
