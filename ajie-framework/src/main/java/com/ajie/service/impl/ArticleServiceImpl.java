package com.ajie.service.impl;

import com.ajie.common.ResponseResult;
import com.ajie.constants.SystemConstants;
import com.ajie.mapper.ArticleMapper;
import com.ajie.model.domain.Article;
import com.ajie.model.domain.ArticleTag;
import com.ajie.model.domain.Category;
import com.ajie.model.dto.AddArticleDto;
import com.ajie.model.vo.*;
import com.ajie.service.ArticleService;
import com.ajie.service.ArticleTagService;
import com.ajie.service.CategoryService;
import com.ajie.utils.BeanCopyUtils;
import com.ajie.utils.RedisCache;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * @author 16515
 * @description 针对表【ajie_article(文章表)】的数据库操作Service实现
 * @createDate 2022-11-11 19:00:21
 */
@Service
public class ArticleServiceImpl extends ServiceImpl<ArticleMapper, Article>
        implements ArticleService {

    @Resource
    private CategoryService categoryService;

    @Resource
    private RedisCache redisCache;

    @Resource
    private ArticleTagService articleTagService;

    @Override
    public ResponseResult getHotArticleList() {
        //查询热门文章 封装成ResponseResult返回
        LambdaQueryWrapper<Article> queryWrapper = new LambdaQueryWrapper<>();
        //必须是正式文章    -----  status == 0
        queryWrapper.eq(Article::getStatus, SystemConstants.ARTICLE_STATUS_NORMAL);
        //按照浏览量进行排序
        queryWrapper.orderByDesc(Article::getViewCount);
        //最多只查询10条
        Page<Article> page = new Page<>(1, 10);

        page(page, queryWrapper);
        List<Article> hotArticles = page.getRecords();

        List<HotArticleVo> hotArticleVos = BeanCopyUtils.copyBeanList(hotArticles, HotArticleVo.class);

        return ResponseResult.okResult(hotArticleVos);
    }

    @Override
    public ResponseResult getArticleList(Integer pageNum, Integer pageSize, Long categoryId) {
        //查询条件
        LambdaQueryWrapper<Article> queryWrapper = new LambdaQueryWrapper<>();
        //如果有categoryId就要查询时和传入值相同
        queryWrapper.eq(Objects.nonNull(categoryId) && categoryId > 0, Article::getCategoryId, categoryId);
        //状态为正式发布的
        queryWrapper.eq(Article::getStatus, SystemConstants.ARTICLE_STATUS_NORMAL);
        //对isTop进行排序
        queryWrapper.orderByDesc(Article::getIsTop);
        //分页查询
        Page<Article> page = new Page<>(pageNum, pageSize);
        page(page, queryWrapper);

        List<Article> articles = page.getRecords();

        //根据articles中的categoryId查询categoryName
        /*
            articles = articles.stream()
                .map(new Function<Article, Article>() {
                    @Override
                    public Article apply(Article article) {
                        //获取分类id，查询分类信息，获取分类名称
                        Category category = categoryService.getById(article.getCategoryId());
                        String categoryName = category.getName();
                        //设置分类名称
                        article.setCategoryName(categoryName);
                        return article;
                    }
                }).collect(Collectors.toList());
         */
        List<Article> articleList = articles.stream()
                .map(article -> article.setCategoryName(categoryService.getById(article.getCategoryId()).getName()))
                .collect(Collectors.toList());

        //封装查询结果
        List<ArticleListVo> articleListVos = BeanCopyUtils.copyBeanList(articleList, ArticleListVo.class);

        PageVo pageVo = new PageVo(articleListVos, page.getTotal());

        return ResponseResult.okResult(pageVo);
    }

    /**
     * 根据id查询文章的详细信息
     *
     * @param id 文章id
     * @return 封装好的需要展示的文章信息
     */
    @Override
    public ResponseResult getArticleDetail(Long id) {
        //根据id查询文章信息
        Article article = this.getById(id);

        //从redis中获取viewCount
        Integer viewCount = redisCache.getCacheMapValue(SystemConstants.UPDATE_ARTICLE_VIEW_COUNT, id.toString());
        article.setViewCount(viewCount.longValue());

        //拷贝文章信息到封装好的vo对象中
        ArticleDetailVo articleDetailVo = BeanCopyUtils.copyBean(article, ArticleDetailVo.class);

        //根据id查询分类信息，并设置到vo对象中
        Long categoryId = articleDetailVo.getCategoryId();
        Category category = categoryService.getById(categoryId);
        if (category != null) {
            articleDetailVo.setCategoryName(category.getName());
        }
        return ResponseResult.okResult(articleDetailVo);
    }

    @Override
    public ResponseResult updateViewCount(Long id) {
        //更新redis中对应id的viewCount信息
        redisCache.incrementCacheMapValue(SystemConstants.UPDATE_ARTICLE_VIEW_COUNT, id.toString(), 1);
        return ResponseResult.okResult();
    }

    @Override
    @Transactional
    public ResponseResult addArticle(AddArticleDto articleDto) {
        //添加 博客
        Article article = BeanCopyUtils.copyBean(articleDto, Article.class);
        save(article);

        //添加 博客和标签的关联
        List<ArticleTag> articleTags = articleDto.getTags().stream()
                .map(tagId -> new ArticleTag(article.getId(), tagId))
                .collect(Collectors.toList());
        articleTagService.saveBatch(articleTags);
        return ResponseResult.okResult();
    }

    @Override
    public ResponseResult getAdminArticleList(Integer pageNum, Integer pageSize, String title, String summary) {
        LambdaQueryWrapper<Article> queryWrapper = new LambdaQueryWrapper<>();

        queryWrapper.like(Objects.nonNull(title), Article::getTitle, title);
        queryWrapper.like(Objects.nonNull(summary), Article::getSummary, summary);
        queryWrapper.orderByDesc(Article::getIsTop);

        Page<Article> page = new Page<>(pageNum, pageSize);
        page(page, queryWrapper);

        List<AdminArticleVo> adminArticleVos = BeanCopyUtils.copyBeanList(page.getRecords(), AdminArticleVo.class);

        PageVo pageVo = new PageVo(adminArticleVos, page.getTotal());

        return ResponseResult.okResult(pageVo);
    }

    @Override
    public ResponseResult getAdminArticle(Long id) {
        Article article = this.getById(id);
        return ResponseResult.okResult(article);
    }

    @Override
    public ResponseResult updateAdminArticle(AddArticleDto articleDto) {
        //更新文章
        Article article = BeanCopyUtils.copyBean(articleDto, Article.class);
        article.setUpdateTime(new Date());
        this.update(article, null);
        return ResponseResult.okResult();
    }

    @Override
    public ResponseResult deleteAdminArticle(Long id) {
        this.removeById(id);
        return ResponseResult.okResult();
    }
}




