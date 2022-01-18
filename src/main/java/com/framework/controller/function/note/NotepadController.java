package com.framework.controller.function.note;

import com.framework.constant.response.Result;
import com.framework.dto.function.NotepadDTO;
import com.framework.entity.function.note.Notepad;
import com.framework.service.function.note.INotepadService;
import com.framework.vo.NotepadVO;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 记事本
 */
@RestController
@RequestMapping("/notepad")
public class NotepadController {

    @Resource
    private INotepadService notepadService;

    /**
     * 查询笔记列表
     * @param notepadDTO
     * @return
     */
    @PostMapping("/list")
    public Result list(@RequestBody NotepadDTO notepadDTO) {
        return notepadService.getNotepadList(notepadDTO);
    }

    /**
     * 获取笔记详情
     * @param notepadDTO
     * @return
     */
    @PostMapping("/detail")
    public Result detail(@RequestBody NotepadDTO notepadDTO) {
        String id = notepadDTO.getId();
        Notepad notepad = notepadService.getById(id);
        NotepadVO notepadVO = new NotepadVO();
        BeanUtils.copyProperties(notepad, notepadVO);
        List<String> tags = new ArrayList<>();
        if (StringUtils.isNotEmpty(notepad.getTags())) {
            tags = Arrays.asList(notepad.getTags().split(","));
        }
        notepadVO.setTags(tags);
        return Result.success(notepadVO);
    }

    /**
     * 新增或保存笔记
     * @param notepadDTO
     * @return
     */
    @PostMapping("/save")
    public Result save(@RequestBody NotepadDTO notepadDTO) {
        return notepadService.saveNotepad(notepadDTO);
    }

    /**
     * 获取tag选项
     * @return
     */
    @GetMapping("/options")
    public Result options() {
        return notepadService.getTagOptions();
    }

    @DeleteMapping("/delete/{id}")
    public Result delete(@PathVariable("id") String id) {
        notepadService.removeById(id);
        return Result.success("删除成功");
    }

}
