package com.framework.service.movie;

import com.baomidou.mybatisplus.extension.service.IService;
import com.framework.entity.movie.Movie;

import java.io.IOException;
import java.util.List;

/**
 * @description: 电影类业务层
 * @author: xingyuzhang
 * @create: 2020-09-15 15:53
 */
public interface IMovieService extends IService<Movie> {

    /**
     * 定时获取电影数据
     */
    void autoGetMovieData() throws IOException;
    
}
