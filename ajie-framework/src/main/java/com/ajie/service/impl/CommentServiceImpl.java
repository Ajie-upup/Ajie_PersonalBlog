package com.ajie.service.impl;

import com.ajie.common.AppHttpCodeEnum;
import com.ajie.common.ResponseResult;
import com.ajie.constants.SystemConstants;
import com.ajie.exception.SystemException;
import com.ajie.mapper.CommentMapper;
import com.ajie.model.domain.Comment;
import com.ajie.model.vo.CommentVo;
import com.ajie.model.vo.PageVo;
import com.ajie.service.CommentService;
import com.ajie.service.UserService;
import com.ajie.utils.BeanCopyUtils;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author 16515
 * @description 针对表【ajie_comment(评论表)】的数据库操作Service实现
 * @createDate 2022-11-16 12:58:48
 */
@Service
public class CommentServiceImpl extends ServiceImpl<CommentMapper, Comment>
        implements CommentService {

    @Resource
    private UserService userService;

    /**
     * 获取文章评论
     *
     * @param commentType 评论类型
     * @param articleId   文章id
     * @param pageNum     页码
     * @param pageSize    页面大小
     * @return 响应给前端
     */
    @Override
    public ResponseResult getCommentList(String commentType, Long articleId, Integer pageNum, Integer pageSize) {
        //查询对应文章的根评论

        LambdaQueryWrapper<Comment> queryWrapper = new LambdaQueryWrapper<>();
        //对articleId判断
        queryWrapper.eq(SystemConstants.ARTICLE_COMMENT.equals(commentType), Comment::getArticleId, articleId);
        queryWrapper.eq(Comment::getRootId, SystemConstants.ARTICLE_ROOT_ID);
        //判断评论类型
        queryWrapper.eq(Comment::getType, commentType);

        //分页查询
        Page<Comment> page = new Page(pageNum, pageSize);
        page(page, queryWrapper);

        //封装返回值对象
        List<CommentVo> commentVos = toCommentVoList(page.getRecords());

        //查询所有根评论对应的子评论集合，并且赋值给对应的属性
//        for (CommentVo commentVo : commentVos) {
//            //查询对应的子评论
//            List<CommentVo> children = getChildren(commentVo.getId());
//            //赋值
//            commentVo.setChildren(children);
//        }
        commentVos.stream()
                .map(commentVo -> commentVo.setChildren(getChildren(commentVo.getId())))
                .collect(Collectors.toList());
        return ResponseResult.okResult(new PageVo(commentVos, page.getTotal()));
    }


    private List<CommentVo> toCommentVoList(List<Comment> list) {
        List<CommentVo> commentVos = BeanCopyUtils.copyBeanList(list, CommentVo.class);

        commentVos.stream()
                .map(commentVo -> {
                    //通过createBy查询用户的昵称并赋值
                    String nickName = userService.getById(commentVo.getCreateBy()).getNickName();
                    commentVo.setUsername(nickName);
                    //通过toCommentUserId查询用户的昵称并赋值
                    //如果toCommentUserId不为-1才进行查询
                    if (commentVo.getToCommentUserId() != -1) {
                        String toCommentUserName = userService.getById(commentVo.getToCommentUserId()).getNickName();
                        commentVo.setToCommentUserName(toCommentUserName);
                    }
                    return commentVo;
                }).collect(Collectors.toList());
//        //遍历vo集合
//        for (CommentVo commentVo : commentVos) {
//            //通过createBy查询用户的昵称并赋值
//            String nickName = userService.getById(commentVo.getCreateBy()).getNickName();
//            commentVo.setUsername(nickName);
//            //通过toCommentUserId查询用户的昵称并赋值
//            //如果toCommentUserId不为-1才进行查询
//            if (commentVo.getToCommentUserId() != -1) {
//                String toCommentUserName = userService.getById(commentVo.getToCommentUserId()).getNickName();
//                commentVo.setToCommentUserName(toCommentUserName);
//            }
//        }
        return commentVos;
    }

    /**
     * 根据根评论的id查询所对应的子评论的集合
     *
     * @param id 根评论的id
     * @return
     */
    private List<CommentVo> getChildren(Long id) {
        LambdaQueryWrapper<Comment> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Comment::getRootId, id);
        queryWrapper.orderByAsc(Comment::getCreateTime);
        List<Comment> comments = list(queryWrapper);

        List<CommentVo> commentVos = toCommentVoList(comments);
        return commentVos;
    }

    /**
     * 添加评论
     *
     * @param comment 用户评论
     * @return 添加成功
     */
    @Override
    public ResponseResult addComment(Comment comment) {
        //评论内容不能为空
        if (!StringUtils.hasText(comment.getContent())) {
            throw new SystemException(AppHttpCodeEnum.CONTENT_NOT_NULL);
        }

        save(comment);

        return ResponseResult.okResult();
    }

}




