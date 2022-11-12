package com.ajie.service;


import com.ajie.common.ResponseResult;
import com.ajie.model.domain.Article;
import com.baomidou.mybatisplus.extension.service.IService;

/**
* @author 16515
* @description 针对表【ajie_article(文章表)】的数据库操作Service
* @createDate 2022-11-11 19:00:21
*/
public interface ArticleService extends IService<Article> {

    ResponseResult getHotArticleList();
}
