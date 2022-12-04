package com.ajie.service.impl;

import com.ajie.mapper.ArticleTagMapper;
import com.ajie.model.domain.ArticleTag;
import com.ajie.service.ArticleTagService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
* @author 16515
* @description 针对表【ajie_article_tag(文章标签关联表)】的数据库操作Service实现
* @createDate 2022-12-03 16:46:44
*/
@Service
public class ArticleTagServiceImpl extends ServiceImpl<ArticleTagMapper, ArticleTag>
    implements ArticleTagService {

}




