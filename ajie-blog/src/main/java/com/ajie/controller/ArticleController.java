package com.ajie.controller;

import com.ajie.common.ResponseResult;
import com.ajie.service.ArticleService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @Author: ajie
 * @Date: 2022/11/11
 */
@RestController
@RequestMapping("/article")
public class ArticleController {

    @Resource
    private ArticleService articleService;

    @GetMapping("/hotArticleList")
    private ResponseResult getHotArticle() {
        ResponseResult result = articleService.getHotArticleList();
        return result;
    }

    @GetMapping("/articleList")
    public ResponseResult getArticleList(Integer pageNum,Integer pageSize,Long categoryId){
        ResponseResult result = articleService.getArticleList(pageNum,pageSize,categoryId);
        return result;
    }
}
