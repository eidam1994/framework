package com.framework.entity.codeValue;

import java.io.Serializable;

import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

/**
 * code_value
 * @author 
 */
@Data
public class CodeValue implements Serializable {
    /**
     * 值列表键值
     */
    @TableId
    private String code;

    /**
     * 值列表值
     */
    private String value;

    private static final long serialVersionUID = 1L;
}