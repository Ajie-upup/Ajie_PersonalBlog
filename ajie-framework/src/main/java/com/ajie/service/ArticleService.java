package com.ajie.service;


import com.ajie.common.ResponseResult;
import com.ajie.model.domain.Article;
import com.ajie.model.dto.AddArticleDto;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * @author 16515
 * @description 针对表【ajie_article(文章表)】的数据库操作Service
 * @createDate 2022-11-11 19:00:21
 */
public interface ArticleService extends IService<Article> {

    ResponseResult getHotArticleList();

    ResponseResult getArticleList(Integer pageNum, Integer pageSize, Long categoryId);

    /**
     * 根据id查询文章的详细信息
     *
     * @param id 文章id
     * @return 封装好的需要展示的文章信息
     */
    ResponseResult getArticleDetail(Long id);

    ResponseResult updateViewCount(Long id);

    ResponseResult addArticle(AddArticleDto articleDto);

    ResponseResult getAdminArticleList(Integer pageNum, Integer pageSize, String title, String summary);

    ResponseResult getAdminArticle(Long id);

    ResponseResult updateAdminArticle(AddArticleDto articleDto);

    ResponseResult deleteAdminArticle(Long id);
}
