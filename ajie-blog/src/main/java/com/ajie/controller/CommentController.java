package com.ajie.controller;

import com.ajie.common.ResponseResult;
import com.ajie.constants.SystemConstants;
import com.ajie.model.domain.Comment;
import com.ajie.service.CommentService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @Author: ajie
 * @Date: 2022/11/16
 */
@RestController
@RequestMapping("/comment")
public class CommentController {

    @Resource
    private CommentService commentService;

    /**
     * 获取文章评论
     *
     * @param articleId 文章id
     * @param pageNum   页码
     * @param pageSize  页面大小
     * @return 响应给前端
     */
    @GetMapping("/commentList")
    public ResponseResult getCommentList(Long articleId, Integer pageNum, Integer pageSize) {
        return commentService.getCommentList(SystemConstants.ARTICLE_COMMENT,articleId, pageNum, pageSize);
    }

    /**
     * 添加评论
     *
     * @param comment 用户评论
     * @return 添加成功
     */
    @PostMapping()
    public ResponseResult addComment(@RequestBody Comment comment) {
        return commentService.addComment(comment);
    }

    /**
     * 友联评论列表
     *
     * @param pageNum  页码
     * @param pageSize 页面大小
     * @return 响应给前端
     */
    @GetMapping("/linkCommentList")
    public ResponseResult getLinkCommentList(Integer pageNum, Integer pageSize) {
        return commentService.getCommentList(SystemConstants.LINK_COMMENT, null,pageNum, pageSize);
    }


}
