package com.ajie.service.impl;

import com.ajie.common.ResponseResult;
import com.ajie.constants.SystemConstants;
import com.ajie.model.domain.LoginUser;
import com.ajie.model.domain.User;
import com.ajie.service.LoginAdminService;
import com.ajie.utils.JwtUtil;
import com.ajie.utils.RedisCache;
import com.ajie.utils.SecurityUtils;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * @Author: ajie
 * @Date: 2022/12/2
 */
@Service
public class LoginAdminServiceImpl implements LoginAdminService {

    @Resource
    private AuthenticationManager authenticationManager;

    @Resource
    private RedisCache redisCache;

    @Override
    public ResponseResult login(User user) {
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(user.getUserName(), user.getPassword());
        Authentication authenticate = authenticationManager.authenticate(authenticationToken);
        //判断是否认证通过
        if (Objects.isNull(authenticate)) {
            throw new RuntimeException("用户名或密码错误");
        }
        //获取userid 生成token
        LoginUser loginUser = (LoginUser) authenticate.getPrincipal();
        String userId = loginUser.getUser().getId().toString();
        String jwt = JwtUtil.createJWT(userId);
        //把用户信息存入redis
        redisCache.setCacheObject(SystemConstants.ADMIN_LOGIN + userId, loginUser);

        //把token封装 返回
        Map<String, String> map = new HashMap<>();
        map.put("token", jwt);
        return ResponseResult.okResult(map);
    }

    @Override
    public ResponseResult logout() {
        //获取当前用户的id
        Long userId = SecurityUtils.getUserId();
        //删除redis中对应的用户信息
        redisCache.deleteObject(SystemConstants.ADMIN_LOGIN + userId);
        return ResponseResult.okResult();
    }
}
