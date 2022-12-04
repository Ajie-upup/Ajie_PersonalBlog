package com.ajie.service.impl;

import com.ajie.common.ResponseResult;
import com.ajie.mapper.TagMapper;
import com.ajie.model.domain.Tag;
import com.ajie.model.dto.TagListDto;
import com.ajie.model.request.AddTagRequest;
import com.ajie.model.vo.PageVo;
import com.ajie.model.vo.TagVo;
import com.ajie.service.TagService;
import com.ajie.utils.BeanCopyUtils;
import com.ajie.utils.SecurityUtils;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Date;
import java.util.List;

/**
 * @author ajie
 * @description 针对表【ajie_tag(标签)】的数据库操作Service实现
 * @createDate 2022-12-02 14:55:09
 */
@Service
public class TagServiceImpl extends ServiceImpl<TagMapper, Tag>
        implements TagService {

    @Override
    public ResponseResult<PageVo> pageTagList(Integer pageNum, Integer pageSize, TagListDto tagListDto) {
        //1、判断dto中的字段是否存在
        LambdaQueryWrapper<Tag> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(StringUtils.hasText(tagListDto.getName()), Tag::getName, tagListDto.getName());
        queryWrapper.eq(StringUtils.hasText(tagListDto.getRemark()), Tag::getRemark, tagListDto.getRemark());


        //2、分页查询
        Page<Tag> page = new Page<>();
        page.setCurrent(pageNum);
        page.setSize(pageSize);
        page(page, queryWrapper);

        //3、返回封装数据
        PageVo pageVo = new PageVo(page.getRecords(), page.getTotal());
        return ResponseResult.okResult(pageVo);
    }

    @Override
    public ResponseResult addTag(AddTagRequest addTagRequest) {
        //获取当前用户
        Long userId = SecurityUtils.getUserId();
        Tag tag = new Tag();
        tag.setName(addTagRequest.getName());
        tag.setRemark(addTagRequest.getRemark());
        tag.setCreateTime(new Date());
        tag.setUpdateTime(new Date());
        tag.setCreateBy(userId);
        tag.setUpdateBy(userId);
        this.save(tag);
        return ResponseResult.okResult();
    }

    @Override
    public ResponseResult deleteTag(Long id) {
        this.removeById(id);
        return ResponseResult.okResult();
    }

    @Override
    public ResponseResult getTag(Long id) {
        Tag tag = this.getById(id);
        //封装要返回的数据
        TagListDto tagListDto = new TagListDto(id, tag.getName(), tag.getRemark());
        return ResponseResult.okResult(tagListDto);
    }

    @Override
    public ResponseResult updateTag(TagListDto tagListDto) {
        Long userId = SecurityUtils.getUserId();
        Tag dbTag = this.getById(tagListDto.getId());
        if (tagListDto.getName() != null) {
            dbTag.setName(tagListDto.getName());
        }
        if (tagListDto.getRemark() != null) {
            dbTag.setRemark(tagListDto.getRemark());
        }
        dbTag.setUpdateBy(userId);
        dbTag.setUpdateTime(new Date());
        this.updateById(dbTag);
        return ResponseResult.okResult();
    }

    @Override
    public List<TagVo> listAllTag() {
        LambdaQueryWrapper<Tag> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.select(Tag::getId, Tag::getName);
        List<Tag> tags = this.list(queryWrapper);
        List<TagVo> tagVos = BeanCopyUtils.copyBeanList(tags, TagVo.class);
        return tagVos;
    }


}




