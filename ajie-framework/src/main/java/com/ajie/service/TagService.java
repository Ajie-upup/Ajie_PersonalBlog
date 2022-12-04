package com.ajie.service;

import com.ajie.common.ResponseResult;
import com.ajie.model.domain.Tag;
import com.ajie.model.dto.TagListDto;
import com.ajie.model.request.AddTagRequest;
import com.ajie.model.vo.PageVo;
import com.ajie.model.vo.TagVo;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
* @author 16515
* @description 针对表【ajie_tag(标签)】的数据库操作Service
* @createDate 2022-12-02 14:55:09
*/
public interface TagService extends IService<Tag> {

    ResponseResult<PageVo> pageTagList(Integer pageNum, Integer pageSize, TagListDto tagListDto);

    ResponseResult addTag(AddTagRequest addTagRequest);

    ResponseResult deleteTag(Long id);

    ResponseResult getTag(Long id);

    ResponseResult updateTag(TagListDto tagListDto);

    List<TagVo> listAllTag();

}
