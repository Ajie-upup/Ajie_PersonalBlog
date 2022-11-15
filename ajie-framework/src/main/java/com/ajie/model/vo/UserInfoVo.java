package com.ajie.model.vo;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @Author: ajie
 * @Date: 2022/11/15
 */
@Data
@Accessors(chain = true)
public class UserInfoVo {
    /**
     * 主键
     */
    private Long id;

    /**
     * 昵称
     */
    private String nickName;

    /**
     * 头像
     */
    private String avatar;

    private String sex;

    private String email;


}
