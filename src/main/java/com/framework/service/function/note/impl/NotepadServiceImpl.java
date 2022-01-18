package com.framework.service.function.note.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.framework.constant.response.Result;
import com.framework.dao.function.note.NotepadMapper;
import com.framework.dto.function.NotepadDTO;
import com.framework.entity.function.note.Notepad;
import com.framework.service.function.note.INotepadService;
import com.framework.utils.UserInfoUtils;
import com.framework.vo.NotepadVO;
import com.framework.vo.UserInfoVO;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.*;

@Service
public class NotepadServiceImpl extends ServiceImpl<NotepadMapper, Notepad> implements INotepadService {

    @Resource
    private NotepadMapper notepadMapper;

    @Override
    public Result getNotepadList(NotepadDTO notepadDTO) {
        UserInfoVO userInfo = UserInfoUtils.getUserInfo();
        if (Objects.nonNull(userInfo)) {
            notepadDTO.setUserId(userInfo.getId());
        }
        return Result.success(notepadMapper.selectNotepadList(notepadDTO));
    }

    @Override
    public Result saveNotepad(NotepadDTO notepadDTO) {
        Notepad notepad = new Notepad();
        BeanUtils.copyProperties(notepadDTO, notepad);
        if (CollectionUtils.isEmpty(notepadDTO.getTags())) {
            notepad.setTags("未分类");
        } else {
            notepad.setTags(String.join(",", notepadDTO.getTags()));
        }
        this.saveOrUpdate(notepad);
        return Result.success(notepad);
    }

    @Override
    public Result getTagOptions() {
        Set<String> tags = new HashSet<>();
        UserInfoVO userInfo = UserInfoUtils.getUserInfo();
        if (Objects.nonNull(userInfo)) {
            // 查询所有不同的标签集
            List<String> tagsOptions = notepadMapper.getTagsOptions(userInfo.getId());
            for (String tagsOption : tagsOptions) {
                if (Objects.nonNull(tagsOption)) {
                    // 保留不同的标签
                    String[] splitTags = tagsOption.split(",");
                    tags.addAll(Arrays.asList(splitTags));
                }
            }
        }
        return Result.success(tags);
    }
}
