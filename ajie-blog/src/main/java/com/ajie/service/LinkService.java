package com.ajie.service;

import com.ajie.common.ResponseResult;
import com.ajie.model.domain.Link;
import com.baomidou.mybatisplus.extension.service.IService;

/**
* @author 16515
* @description 针对表【ajie_link(友链)】的数据库操作Service
* @createDate 2022-11-14 13:26:46
*/
public interface LinkService extends IService<Link> {

    /**
     * 获取并展示已经发布成功的友链表信息
     * @return 封装好的信息
     */
    ResponseResult getAllLink();

}
