package com.ajie.service.impl;

import com.ajie.common.ResponseResult;
import com.ajie.mapper.UserMapper;
import com.ajie.model.domain.User;
import com.ajie.model.vo.UserInfoVo;
import com.ajie.service.UserService;
import com.ajie.utils.BeanCopyUtils;
import com.ajie.utils.SecurityUtils;
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
    /**
     * 获取用户信息
     *
     * @return
     */
    @Override
    public ResponseResult getUserInfo() {
        //获取当前用户id
        Long userId = SecurityUtils.getUserId();
        //根据用户id查询用户信息
        User user = this.getById(userId);
        //封装数据
        UserInfoVo userInfoVo = BeanCopyUtils.copyBean(user, UserInfoVo.class);
        return ResponseResult.okResult(userInfoVo);
    }
}




