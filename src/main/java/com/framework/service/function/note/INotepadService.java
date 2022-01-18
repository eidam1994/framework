package com.framework.service.function.note;

import com.baomidou.mybatisplus.extension.service.IService;
import com.framework.constant.response.Result;
import com.framework.dto.function.NotepadDTO;
import com.framework.entity.function.note.Notepad;

public interface INotepadService extends IService<Notepad> {

    /**
     * 获取笔记列表
     * @param notepadDTO
     * @return
     */
    Result getNotepadList(NotepadDTO notepadDTO);

    /**
     * 保存笔记
     * @param notepadDTO
     * @return
     */
    Result saveNotepad(NotepadDTO notepadDTO);

    /**
     * 获取全部标签列表
     * @return
     */
    Result getTagOptions();

}
