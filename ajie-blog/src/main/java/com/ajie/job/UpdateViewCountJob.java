package com.ajie.job;

import com.ajie.constants.SystemConstants;
import com.ajie.model.domain.Article;
import com.ajie.service.ArticleService;
import com.ajie.utils.RedisCache;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @Author: ajie
 * @Date: 2022/12/1
 */
@Component
public class UpdateViewCountJob {

    @Resource
    private RedisCache redisCache;

    @Resource
    private ArticleService articleService;

    //进行测试，代表从0秒开始，每隔5秒执行一次。
    @Scheduled(cron = "0/5 * * * * ?")
    public void updateViewCount() {
        //获取redis中的用户浏览量viewCount
        Map<String, Integer> viewCountMap = redisCache.getCacheMap(SystemConstants.UPDATE_ARTICLE_VIEW_COUNT);
        List<Article> articles = viewCountMap.entrySet()
                .stream()
                .map(entry -> new Article(Long.valueOf(entry.getKey()), entry.getValue().longValue()))
                .collect(Collectors.toList());

        //将更新的数据添加到数据库中
        articleService.updateBatchById(articles);

    }
}
