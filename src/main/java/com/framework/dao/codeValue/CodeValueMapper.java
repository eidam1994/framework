package com.framework.dao.codeValue;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.framework.entity.codeValue.CodeValue;

/**
 * 值列表mapper
 * @author Admin
 */
public interface CodeValueMapper extends BaseMapper<CodeValue> {

    /**
     * 根据键查询对应的值
     * @param code
     * @return
     */
    CodeValue selectValueByCode(String code);
    
}