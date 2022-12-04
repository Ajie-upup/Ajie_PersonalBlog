package com.ajie.service;

import com.ajie.model.domain.Menu;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * @author 16515
 * @description 针对表【sys_menu(菜单权限表)】的数据库操作Service
 * @createDate 2022-12-02 17:28:30
 */
public interface MenuService extends IService<Menu> {

    List<String> selectPermsByUserId(Long id);

    List<Menu> selectRouterMenuTreeByUserId(Long userId);


    List<Menu> selectMenuList(Menu menu);

    boolean hasChild(Long menuId);

    List<Long> selectMenuListByRoleId(Long roleId);
}
