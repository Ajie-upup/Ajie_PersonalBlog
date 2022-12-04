package com.ajie.mapper;

import com.ajie.model.domain.Role;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
* @author 16515
* @description 针对表【sys_role(角色信息表)】的数据库操作Mapper
* @createDate 2022-12-02 17:34:31
* @Entity generator.domain.Role
*/
public interface RoleMapper extends BaseMapper<Role> {

    List<String> selectRoleKeyByUserId(Long id);
}




