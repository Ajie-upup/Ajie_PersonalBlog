package com.ajie.service;

import com.ajie.common.ResponseResult;
import com.ajie.model.domain.User;

/**
 * @Author: ajie
 * @Date: 2022/11/15
 */
public interface BlogLoginService {
    ResponseResult login(User user);


    ResponseResult logout();

}
