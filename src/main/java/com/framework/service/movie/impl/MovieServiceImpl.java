package com.framework.service.movie.impl;

import com.baomidou.mybatisplus.extension.service.IService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.framework.constant.exception.CustomExceptionType;
import com.framework.dao.codeValue.CodeValueMapper;
import com.framework.dao.movie.MovieMapper;
import com.framework.entity.codeValue.CodeValue;
import com.framework.entity.movie.Movie;
import com.framework.exception.CustomException;
import com.framework.service.movie.IMovieService;
import com.framework.utils.DateUtils;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.List;
import java.util.Objects;

/**
 * @description: 电影类业务实现层
 * @author: xingyuzhang
 * @create: 2020-09-15 15:54
 */
@Service
public class MovieServiceImpl extends ServiceImpl<MovieMapper, Movie> implements IMovieService {

    @Resource
    private CodeValueMapper codeValueMapper;

    @Resource
    private MovieMapper movieMapper;

    @Override
    @Scheduled(fixedDelay = 6 * 60 * 60 * 1000)
    public void autoGetMovieData() throws IOException {
        // 获取爬取电影时间戳
        CodeValue lastMovieDate = codeValueMapper.selectValueByCode("LAST_MOVIE_DATE");
        for (int i = 1; i < Integer.MAX_VALUE; i++) {
            String url = "https://www.pianku.li/mv/------" + i + ".html";
            Connection con = Jsoup.connect(url).userAgent(
                    "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/77.0.3865.90 Safari/537.36")
                    .timeout(30000);
            Document document = con.get();
            // 获取电影列表模块
            Elements li = document.getElementsByClass("content-list").get(0).getElementsByTag("li");
            if (li.size() > 0) {
                Movie movie = new Movie();
                for (Element element : li) {
                    try {
                        // 电影信息模块
                        Elements cover = element.getElementsByClass("cover");
                        Elements a = cover.get(0).getElementsByTag("a");
                        // 详情页地址
                        String movieUrl = a.get(0).attr("href");
                        // 获取电影详情信息
                        if (Objects.nonNull(lastMovieDate.getValue()) && isLatestMovie(movieUrl, lastMovieDate.getValue(), movie)) {
                            updateLastDate(lastMovieDate);
                            return;
                        }
                        movie.setUrl(movieUrl);
                        // 电影名称
                        movie.setTitle(a.get(0).attr("title"));
                        // 电影封面
                        movie.setCover(a.get(0).getElementsByTag("img").get(0).attr("data-funlazy"));
                        // 电影豆瓣评分
                        movie.setRate(element.getElementsByClass("li-bottom").get(0)
                                .getElementsByTag("h3").get(0)
                                .getElementsByTag("span").get(0).text());
                        // 爬取网站对应电影id
                        String movieId = cover.get(0).attr("data-id");
                        movie.setMovieId(movieId);
                        // 查询电影是否已经存在
                        Movie queryMovie = movieMapper.selectByMovieId(movieId);
                        if (Objects.nonNull(queryMovie)) {
                            // 更新电影
                            movie.setId(queryMovie.getId());
                            this.updateById(movie);
                        } else {
                            // 新增电影
                            this.save(movie);
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                        continue;
                    }
                }
            } else {
                updateLastDate(lastMovieDate);
                return;
            }
            try {
                // 获取列表间隔20秒
                Thread.sleep(20000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
    
    private void updateLastDate(CodeValue lastMovieDate) {
        // 修改最后时间戳为前天
        String lastDate = DateUtils.getTwoDaysAgoString();
        lastMovieDate.setValue(lastDate);
        codeValueMapper.updateById(lastMovieDate);
    }

    /**
     * 获取电影详情信息
     *
     * @param url
     * @param lastDate
     * @return
     * @throws IOException
     */
    private Boolean isLatestMovie(String url, String lastDate, Movie movie) throws IOException {
        url = "https://www.pianku.tv/" + url;
        Connection con = Jsoup.connect(url).userAgent(
                "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/77.0.3865.90 Safari/537.36")
                .timeout(30000);
        Document document = con.get();
        Elements otherbox = document.getElementsByClass("otherbox");
        Elements em = otherbox.get(0).getElementsByTag("em");
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        if (lastDate.equals(em.get(1).text())) {
            return true;
        } else {
            movie.setMovieAlias("");
            Elements elements = document.getElementsByClass("main-ui-meta");
            Elements divs = elements.get(0).getElementsByTag("div");
            for (Element div : divs) {
                String name = "";
                if (div.getElementsByTag("span").size() > 0) {
                    name = div.getElementsByTag("span").get(0).text();
                }
                if (name.contains("导演")) {
                    // 导演
                    Elements directors = div.getElementsByTag("a");
                    movie.setDirector(createTextBuilder(directors));
                } else if (name.contains("编剧")) {
                    // 编剧
                    Elements screenwriters = div.getElementsByTag("a");
                    movie.setScreenwriter(createTextBuilder(screenwriters));
                } else if (name.contains("主演")) {
                    // 主演
                    Elements starring = div.getElementsByTag("a");
                    movie.setStarring(createTextBuilder(starring));
                } else if (name.contains("类型")) {
                    // 类型
                    Elements types = div.getElementsByTag("a");
                    movie.setMovieType(createTextBuilder(types));
                } else if (name.contains("地区")) {
                    // 地区
                    Elements areas = div.getElementsByTag("a");
                    movie.setArea(createTextBuilder(areas));
                } else if (name.contains("语言")) {
                    // 语言
                    Elements languages = div.getElementsByTag("a");
                    movie.setMovieLanguage(createTextBuilder(languages));
                } else if (name.contains("上映")) {
                    // 上映日期
                    String dates = div.ownText();
                    movie.setScreenedDate(dates.replaceAll(" ", ""));
                } else if (name.contains("片长")) {
                    // 片长
                    String length = div.ownText();
                    movie.setMovieLength(length.replaceAll(" ", ""));
                } else if (name.contains("又名")) {
                    // 又名
                    String alias = div.ownText();
                    movie.setMovieAlias(alias.replaceAll(" ", ""));
                }
            }
            // 介绍
            Elements introductionDiv = document.getElementsByClass("movie-introduce").get(0).getElementsByTag("p");
            String introduction = "";
            if (introductionDiv.size() > 1) {
                introduction = document.getElementsByClass("sqjj_a").get(0).ownText();
            } else {
                introduction = introductionDiv.get(0).ownText();
            }
            movie.setMovieIntroduction(introduction);
            return false;
        }
    }

    private String createTextBuilder(Elements elements) {
        int count = 0;
        StringBuilder textBuilder = new StringBuilder();
        for (Element element : elements) {
            textBuilder.append(element.text());
            if ((elements.size() - 1 > count)) {
                textBuilder.append("/");
                count++;
            }
        }
        return textBuilder.toString();
    }
}
