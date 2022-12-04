package com.ajie.controller;

import com.ajie.common.ResponseResult;
import com.ajie.model.dto.AddArticleDto;
import com.ajie.service.ArticleService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @Author: ajie
 * @Date: 2022/12/3
 */
@RestController
@RequestMapping("/content/article")
public class AdminArticleController {
    @Resource
    private ArticleService articleService;

    @PostMapping
    public ResponseResult addArticle(@RequestBody AddArticleDto articleDto) {
        return articleService.addArticle(articleDto);
    }

    @GetMapping("/list")
    public ResponseResult getAdminArticleList(Integer pageNum, Integer pageSize, String title, String summary) {
        return articleService.getAdminArticleList(pageNum, pageSize, title, summary);
    }

    @GetMapping()
    public ResponseResult getAdminArticle(@RequestParam("id") Long id){
        return articleService.getAdminArticle(id);
    }

    @PutMapping()
    public ResponseResult updateAdminArticle(@RequestBody AddArticleDto articleDto){
        return articleService.updateAdminArticle(articleDto);
    }

    @DeleteMapping
    public ResponseResult deleteAdminArticle(@RequestParam("id") Long id){
        return articleService.deleteAdminArticle(id);
    }



}
