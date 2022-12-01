package com.ajie.controller;

import com.ajie.annotation.SystemLog;
import com.ajie.common.ResponseResult;
import com.ajie.model.domain.User;
import com.ajie.service.UserService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @Author: ajie
 * @Date: 2022/11/16
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @Resource
    private UserService userService;

    /**
     * 获取用户信息
     *
     * @return
     */
    @GetMapping("/userInfo")
    public ResponseResult getUserInfo() {
        return userService.getUserInfo();
    }

    /**
     * 修改用户信息
     *
     * @return
     */
    @PutMapping("/userInfo")
    @SystemLog(businessName = "修改用户信息")
    public ResponseResult updateUserInfo(@RequestBody User user) {
        return userService.updateUserInfo(user);
    }

    /**
     * 用户注册
     *
     * @param user
     * @return
     */
    @PostMapping("/register")
    public ResponseResult register(@RequestBody User user) {
        return userService.register(user);
    }

}
