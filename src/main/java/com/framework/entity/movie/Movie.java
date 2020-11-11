package com.framework.entity.movie;

import java.io.Serializable;
import java.util.Date;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * movie
 *
 * @author
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Movie implements Serializable {
    
    /**
     * 主键id
     */
    @TableId(type = IdType.AUTO)
    private Long id;

    /**
     * 评分
     */
    private String rate;

    /**
     * 电影名称
     */
    private String title;

    /**
     * 豆瓣详情页链接
     */
    private String url;

    /**
     * 豆瓣电影id
     */
    private String movieId;

    /**
     * 电影封面
     */
    private String cover;

    /**
     * 创建时间
     */
    @TableField(fill = FieldFill.INSERT)
    private Date createTime;

    /**
     * 修改时间
     */
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date updateTime;

    /**
     * 导演
     */
    private String director;

    /**
     * 编剧
     */
    private String screenwriter;

    /**
     * 主演
     */
    private String starring;

    /**
     * 电影类型
     */
    private String movieType;

    /**
     * 地区
     */
    private String area;

    /**
     * 语言
     */
    private String movieLanguage;

    /**
     * 上映日期
     */
    private String screenedDate;

    /**
     * 时长
     */
    private String movieLength;

    /**
     * 别名
     */
    private String movieAlias;

    /**
     * 电影介绍
     */
    private String movieIntroduction;

    private static final long serialVersionUID = 1L;
}