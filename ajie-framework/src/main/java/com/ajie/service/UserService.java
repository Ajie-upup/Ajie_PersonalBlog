package com.ajie.service;

import com.ajie.common.ResponseResult;
import com.ajie.model.domain.User;
import com.baomidou.mybatisplus.extension.service.IService;

/**
* @author 16515
* @description 针对表【sys_user(用户表)】的数据库操作Service
* @createDate 2022-11-16 13:29:16
*/
public interface UserService extends IService<User> {
    /**
     * 获取用户信息
     *
     * @return
     */
    ResponseResult getUserInfo();

    ResponseResult updateUserInfo(User user);

    ResponseResult register(User user);
}
