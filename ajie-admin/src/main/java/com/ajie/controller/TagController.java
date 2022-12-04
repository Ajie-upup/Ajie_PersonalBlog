package com.ajie.controller;

import com.ajie.common.ResponseResult;
import com.ajie.model.dto.TagListDto;
import com.ajie.model.request.AddTagRequest;
import com.ajie.model.vo.PageVo;
import com.ajie.model.vo.TagVo;
import com.ajie.service.TagService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Author: ajie
 * @Date: 2022/12/2
 */
@RestController
@RequestMapping("/content/tag")
public class TagController {

    @Resource
    private TagService tagService;

    /**
     * 查询标签列表
     *
     * @param pageNum    当前页面
     * @param pageSize   页面大小
     * @param tagListDto 根据条件查询
     * @return
     */
    @GetMapping("/list")
    public ResponseResult<PageVo> list(Integer pageNum, Integer pageSize, TagListDto tagListDto) {
        return tagService.pageTagList(pageNum, pageSize, tagListDto);
    }

    @PostMapping()
    public ResponseResult addTag(@RequestBody AddTagRequest addTagRequest) {
        if (addTagRequest.getName() == null && addTagRequest.getRemark() == null) {
            throw new RuntimeException("请求参数不能为空");
        }
        return tagService.addTag(addTagRequest);
    }

    /**
     * 删除标签信息
     *
     * @param id 标签id
     * @return
     */
    @DeleteMapping()
    public ResponseResult deleteTag(@RequestParam("id") Long id) {
        if (id == null) {
            throw new RuntimeException("请求参数不能为空");
        }
        return tagService.deleteTag(id);
    }

    @GetMapping()
    public ResponseResult getTag(@RequestParam("id") Long id) {
        if (id == null) {
            throw new RuntimeException("请求参数不能为空");
        }
        return tagService.getTag(id);
    }

    @PutMapping()
    public ResponseResult updateTag(@RequestBody TagListDto tagListDto) {
        if (tagListDto == null) {
            throw new RuntimeException("请求参数不能为空");
        }
        if (tagListDto.getId() == null) {
            throw new RuntimeException("tag不存在");
        }
        return tagService.updateTag(tagListDto);
    }

    @GetMapping("/listAllTag")
    public ResponseResult listAllTag() {
        List<TagVo> list = tagService.listAllTag();
        return ResponseResult.okResult(list);
    }

}
