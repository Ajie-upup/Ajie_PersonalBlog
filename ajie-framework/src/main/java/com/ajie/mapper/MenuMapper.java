package com.ajie.mapper;

import com.ajie.model.domain.Menu;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
* @author 16515
* @description 针对表【sys_menu(菜单权限表)】的数据库操作Mapper
* @createDate 2022-12-02 17:28:30
* @Entity generator.domain.Menu
*/
public interface MenuMapper extends BaseMapper<Menu> {

    List<String> selectPermsByUserId(Long id);

    List<Menu> selectAllRouterMenu();

    List<Menu> selectRouterMenuTreeByUserId(Long userId);

    List<Long> selectMenuListByRoleId(Long roleId);
}




