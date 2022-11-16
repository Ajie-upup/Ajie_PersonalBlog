package com.ajie.service.impl;

import com.ajie.mapper.UserMapper;
import com.ajie.model.domain.User;
import com.ajie.service.UserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
* @author 16515
* @description 针对表【sys_user(用户表)】的数据库操作Service实现
* @createDate 2022-11-16 13:29:16
*/
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User>
    implements UserService {

}




