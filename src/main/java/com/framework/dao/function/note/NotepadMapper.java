package com.framework.dao.function.note;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.framework.dto.function.NotepadDTO;
import com.framework.entity.function.note.Notepad;
import com.framework.vo.NotepadVO;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NotepadMapper extends BaseMapper<Notepad> {

    /**
     * 查询笔记列表
     * @param notepadDTO
     * @return
     */
    List<NotepadVO> selectNotepadList(NotepadDTO notepadDTO);

    /**
     * 获取所有标签集合
     * @return
     */
    List<String> getTagsOptions(String userId);

}
