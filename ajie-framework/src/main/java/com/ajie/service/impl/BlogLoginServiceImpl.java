package com.ajie.service.impl;

import com.ajie.common.ResponseResult;
import com.ajie.constants.SystemConstants;
import com.ajie.model.domain.LoginUser;
import com.ajie.model.domain.User;
import com.ajie.model.vo.BlogUserLoginVo;
import com.ajie.model.vo.UserInfoVo;
import com.ajie.service.BlogLoginService;
import com.ajie.utils.BeanCopyUtils;
import com.ajie.utils.JwtUtil;
import com.ajie.utils.RedisCache;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Objects;

/**
 * @Author: ajie
 * @Date: 2022/11/15
 */
@Service
public class BlogLoginServiceImpl implements BlogLoginService {

    @Resource
    private AuthenticationManager authenticationManager;

    @Resource
    private RedisCache redisCache;

    @Override
    public ResponseResult login(User user) {
        String userName = user.getUserName();
        String password = user.getPassword();
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(userName, password);
        Authentication authentication = authenticationManager.authenticate(authenticationToken);

        //判断是否认真通过
        if (Objects.isNull(authentication)) {
            throw new RuntimeException("用户名或密码错误");
        }
        //获取userid，生成token
        LoginUser loginUser = (LoginUser) authentication.getPrincipal();
        Long userId = loginUser.getUser().getId();
        String jwt = JwtUtil.createJWT(userId + "");
        //把用户信息存入到redis中
        redisCache.setCacheObject(SystemConstants.BLOG_LOGIN + userId, loginUser);

        //把token和userinfo封装，返回
        UserInfoVo userInfoVo = BeanCopyUtils.copyBean(loginUser.getUser(), UserInfoVo.class);

        BlogUserLoginVo blogUserLoginVo = new BlogUserLoginVo(jwt, userInfoVo);

        return ResponseResult.okResult(blogUserLoginVo);
    }

    @Override
    public ResponseResult logout() {
        //获取token，解析获取userid
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        LoginUser loginUser = (LoginUser) authentication.getPrincipal();
        Long userId = loginUser.getUser().getId();
        //删除redis中的用户信息
        redisCache.deleteObject(SystemConstants.BLOG_LOGIN + userId);
        return ResponseResult.okResult();
    }
}
