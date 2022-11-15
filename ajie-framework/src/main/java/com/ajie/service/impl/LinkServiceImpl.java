package com.ajie.service.impl;

import com.ajie.common.ResponseResult;
import com.ajie.constants.SystemConstants;
import com.ajie.mapper.LinkMapper;
import com.ajie.model.domain.Link;
import com.ajie.model.vo.LinkVo;
import com.ajie.service.LinkService;
import com.ajie.utils.BeanCopyUtils;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author 16515
 * @description 针对表【ajie_link(友链)】的数据库操作Service实现
 * @createDate 2022-11-14 13:26:46
 */
@Service
public class LinkServiceImpl extends ServiceImpl<LinkMapper, Link>
        implements LinkService {

    /**
     * 获取并展示已经发布成功的友链表信息
     *
     * @return 封装好的信息
     */
    @Override
    public ResponseResult getAllLink() {
        //查询友链表已经发布
        LambdaQueryWrapper<Link> queryWrapper = new LambdaQueryWrapper();
        queryWrapper.eq(Link::getStatus, SystemConstants.LINK_AUDIT_PASS);
        List<Link> links = this.list(queryWrapper);

        //封装需要展示的vo对象
        List<LinkVo> linkVos = BeanCopyUtils.copyBeanList(links, LinkVo.class);
        return ResponseResult.okResult(linkVos);
    }
}




