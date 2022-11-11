package com.ajie.service.impl;

import com.ajie.mapper.ArticleMapper;
import com.ajie.model.domain.Article;
import com.ajie.service.ArticleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
* @author 16515
* @description 针对表【ajie_article(文章表)】的数据库操作Service实现
* @createDate 2022-11-11 19:00:21
*/
@Service
public class ArticleServiceImpl extends ServiceImpl<ArticleMapper, Article>
    implements ArticleService{

}




