package com.ajie.controller;

import com.ajie.common.ResponseResult;
import com.ajie.service.ArticleService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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

    /**
     * 查询热门文章表
     *
     * @return 文章id
     */
    @GetMapping("/hotArticleList")
    private ResponseResult getHotArticle() {
        return articleService.getHotArticleList();
    }

    /**
     * 获取首页文章信息 （包含分页）
     *
     * @param pageNum    第几页
     * @param pageSize   页面大小
     * @param categoryId 文章分类的id（可以为空）
     * @return 封装好的包含文章name的vo对象
     */
    @GetMapping("/articleList")
    public ResponseResult getArticleList(Integer pageNum, Integer pageSize, Long categoryId) {
        return articleService.getArticleList(pageNum, pageSize, categoryId);
    }

    /**
     * 根据id查询文章的详细信息
     *
     * @param id 文章id
     * @return 封装好的需要展示的文章信息
     */
    @GetMapping("/{id}")
    public ResponseResult getArticleDetail(@PathVariable("id") Long id) {
        return articleService.getArticleDetail(id);
    }
}
