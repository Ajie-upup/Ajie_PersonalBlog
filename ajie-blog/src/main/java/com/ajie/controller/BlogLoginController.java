package com.ajie.controller;

import com.ajie.common.AppHttpCodeEnum;
import com.ajie.common.ResponseResult;
import com.ajie.exception.SystemException;
import com.ajie.model.domain.User;
import com.ajie.service.BlogLoginService;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @Author: ajie
 * @Date: 2022/11/14
 */
@RestController
public class BlogLoginController {

    @Resource
    private BlogLoginService blogLoginService;

    @PostMapping("/login")
    public ResponseResult login(@RequestBody User user) {
        if (!StringUtils.hasText(user.getUserName())) {
            //提示必须用户名不能为空
            throw new SystemException(AppHttpCodeEnum.REQUIRE_USERNAME);
        }
        return blogLoginService.login(user);
    }

    @PostMapping("/logout")
    public ResponseResult logout() {

        return blogLoginService.logout();
    }


}
