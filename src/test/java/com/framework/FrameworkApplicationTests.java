package com.framework;

import com.framework.entity.movie.Movie;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.util.ByteSource;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


@SpringBootTest
class FrameworkApplicationTests {

    @Test
    void contextLoads() throws IOException {
        String s = new SimpleHash("md5", "1", ByteSource.Util.bytes("eidam"), 2).toHex();
        System.out.println(s);
    }
    
    

}
