package com.ajie.service;

import com.ajie.common.ResponseResult;
import com.ajie.model.domain.Comment;
import com.baomidou.mybatisplus.extension.service.IService;

/**
* @author 16515
* @description 针对表【ajie_comment(评论表)】的数据库操作Service
* @createDate 2022-11-16 12:58:48
*/
public interface CommentService extends IService<Comment> {
    /**
     * 获取文章评论
     *
     * @param commentType 评论类型
     * @param articleId   文章id
     * @param pageNum     页码
     * @param pageSize    页面大小
     * @return 响应给前端
     */
    ResponseResult getCommentList(String commentType, Long articleId, Integer pageNum, Integer pageSize);

    /**
     * 添加评论
     * @param comment 用户评论
     * @return 添加成功
     */
    ResponseResult addComment(Comment comment);
}
