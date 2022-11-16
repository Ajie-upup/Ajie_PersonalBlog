package com.ajie.model.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author: ajie
 * @Date: 2022/11/16
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AddCommentRequest {
    private Long articleId;
    private String type;
    private Long rootId;
    private Long toCommentId;
    private Long toCommentUserId;
    private String comment;
}
