package com.ajie.service.impl;

import com.ajie.constants.SystemConstants;
import com.ajie.mapper.RoleMapper;
import com.ajie.model.domain.Role;
import com.ajie.service.RoleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
* @author 16515
* @description 针对表【sys_role(角色信息表)】的数据库操作Service实现
* @createDate 2022-12-02 17:34:31
*/
@Service
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role>
    implements RoleService {

    @Override
    public List<String> selectRoleKeyByUserId(Long id) {
        //判断是否为管理员 如果是返回集合中只有admin
        if(id == SystemConstants.IS_ADMIN){
            List<String> roleKeys = new ArrayList<>();
            roleKeys.add("admin");
            return roleKeys;
        }
        //否则查询用户所具有的角色信息
        return this.getBaseMapper().selectRoleKeyByUserId(id);
    }
}




