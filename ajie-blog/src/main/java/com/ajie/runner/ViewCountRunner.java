package com.ajie.runner;

import com.ajie.constants.SystemConstants;
import com.ajie.mapper.ArticleMapper;
import com.ajie.model.domain.Article;
import com.ajie.utils.RedisCache;
import org.springframework.boot.CommandLineRunner;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @Author: ajie
 * @Date: 2022/12/1
 */
//@Component
public class ViewCountRunner implements CommandLineRunner {

    @Resource
    private ArticleMapper articleMapper;

    @Resource
    private RedisCache redisCache;

    @Override
    public void run(String... args) throws Exception {
        //查询博客信息 --  id viewCount
        List<Article> articles = articleMapper.selectList(null);
        Map<String, Integer> viewCountMap = articles.stream().collect(Collectors.toMap(article -> article.getId().toString(), article -> {
            return article.getViewCount().intValue();
        }));
        //存储到redis中
        redisCache.setCacheMap(SystemConstants.UPDATE_ARTICLE_VIEW_COUNT, viewCountMap);
    }
}
