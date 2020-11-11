package com.framework.dao.movie;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.framework.entity.movie.Movie;


/**
 * @author Admin
 */
public interface MovieMapper extends BaseMapper<Movie> {

    /**
     * 根据爬取电影id查询是否存在
     * @param movieId
     * @return
     */
    Movie selectByMovieId(String movieId);
    
}