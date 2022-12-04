package com.ajie.model.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.util.List;

/**
 * @Author: ajie
 * @Date: 2022/12/2
 */
@Data
@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor
public class AdminUserInfoVo {
    /**
     * 用户权限信息
     */
    private List<String> permissions;
    /**
     * 用户角色
     */
    private List<String> roles;
    /**
     * 用户信息
     */
    private UserInfoVo user;
}
