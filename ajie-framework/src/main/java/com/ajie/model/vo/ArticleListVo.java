package com.ajie.model.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @Author: ajie
 * @Date: 2022/11/12
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ArticleListVo {

    private Long id;

    private String title;

    private String summary;

    private Long categoryName;

    private String thumbnail;


    private Long viewCount;


    private Date createTime;


}
