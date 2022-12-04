package com.ajie.controller;

import com.ajie.common.AppHttpCodeEnum;
import com.ajie.common.ResponseResult;
import com.ajie.exception.SystemException;
import com.ajie.model.domain.LoginUser;
import com.ajie.model.domain.Menu;
import com.ajie.model.domain.User;
import com.ajie.model.vo.AdminUserInfoVo;
import com.ajie.model.vo.RoutersVo;
import com.ajie.model.vo.UserInfoVo;
import com.ajie.service.LoginAdminService;
import com.ajie.service.MenuService;
import com.ajie.service.RoleService;
import com.ajie.utils.BeanCopyUtils;
import com.ajie.utils.SecurityUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Author: ajie
 * @Date: 2022/12/2
 */
@RestController
public class LoginAdminController {
    @Resource
    private LoginAdminService loginService;

    @Resource
    private MenuService menuService;

    @Resource
    private RoleService roleService;

    @PostMapping("/user/login")
    public ResponseResult login(@RequestBody User user) {
        if (!StringUtils.hasText(user.getUserName())) {
            //提示 必须要传用户名
            throw new SystemException(AppHttpCodeEnum.REQUIRE_USERNAME);
        }
        return loginService.login(user);
    }

    @GetMapping("/getInfo")
    public ResponseResult<AdminUserInfoVo> getInfo() {
        //获取当前登录的用户
        LoginUser loginUser = SecurityUtils.getLoginUser();
        //根据用户id查询权限信息
        List<String> perms = menuService.selectPermsByUserId(loginUser.getUser().getId());
        //根据用户id查询角色信息
        List<String> roleKeyList = roleService.selectRoleKeyByUserId(loginUser.getUser().getId());

        //获取用户信息
        User user = loginUser.getUser();
        UserInfoVo userInfoVo = BeanCopyUtils.copyBean(user, UserInfoVo.class);

        //封装数据返回
        AdminUserInfoVo adminUserInfoVo = new AdminUserInfoVo(perms, roleKeyList, userInfoVo);
        return ResponseResult.okResult(adminUserInfoVo);
    }

    @GetMapping("/getRouters")
    public ResponseResult<RoutersVo> getRouters() {
        Long userId = SecurityUtils.getUserId();
        //查询menu，返回菜单树
        List<Menu> menus = menuService.selectRouterMenuTreeByUserId(userId);
        //封装数据
        return ResponseResult.okResult(new RoutersVo(menus));
    }

    @PostMapping("/user/logout")
    public ResponseResult logout() {
        return loginService.logout();
    }



}
