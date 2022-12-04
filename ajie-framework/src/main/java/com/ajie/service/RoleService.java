package com.ajie.service;

import com.ajie.model.domain.Role;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
* @author 16515
* @description 针对表【sys_role(角色信息表)】的数据库操作Service
* @createDate 2022-12-02 17:34:31
*/
public interface RoleService extends IService<Role> {

    List<String> selectRoleKeyByUserId(Long id);
}
