package com.ajie.service.impl;

import com.ajie.common.ResponseResult;
import com.ajie.constants.SystemConstants;
import com.ajie.mapper.ArticleMapper;
import com.ajie.model.domain.Article;
import com.ajie.model.vo.HotArticleVo;
import com.ajie.service.ArticleService;
import com.ajie.utils.BeanCopyUtils;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author 16515
 * @description 针对表【ajie_article(文章表)】的数据库操作Service实现
 * @createDate 2022-11-11 19:00:21
 */
@Service
public class ArticleServiceImpl extends ServiceImpl<ArticleMapper, Article>
        implements ArticleService {

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
}



