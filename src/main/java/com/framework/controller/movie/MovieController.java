package com.framework.controller.movie;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.framework.constant.exception.CustomExceptionType;
import com.framework.constant.response.Result;
import com.framework.entity.movie.Movie;
import com.framework.exception.CustomException;
import com.framework.service.movie.IMovieService;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * @description: 电影类视图层
 * @author: xingyuzhang
 * @create: 2020-09-17 11:23
 */
@RestController
public class MovieController {

    @Resource
    private IMovieService movieService;

    @GetMapping("/v1/movies")
    public Result getAllMovies(Page<Movie> moviePage) {
        Page<Movie> page = movieService.page(moviePage);
        return Result.success(page);
    }

    @Cacheable(value = "movie" ,key = "#id")
    @GetMapping("/v1/movies/{id}")
    public Result getMovieById(@PathVariable String id) {
        Movie movie = movieService.getById(id);
        return Result.success(movie);
    }

    @CachePut(value = "movie" ,key = "#movie.id")
    @PutMapping("/v1/movies")
    public Result updateMovieById(@RequestBody Movie movie) {
        if (movieService.updateById(movie)) {
            Movie movieResult = movieService.getById(movie.getId());
            return Result.success(movieResult);   
        } else {
            return Result.error(new CustomException(CustomExceptionType.SERVICE_ERROR, "修改失败"));
        }
    }

}
