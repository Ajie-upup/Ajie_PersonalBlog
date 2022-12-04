package com.ajie.service;


import com.ajie.common.ResponseResult;
import com.ajie.model.domain.Category;
import com.ajie.model.vo.CategoryVo;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
* @author 16515
* @description 针对表【ajie_category(分类表)】的数据库操作Service
* @createDate 2022-11-12 19:51:40
*/
public interface CategoryService extends IService<Category> {

    ResponseResult getCategoryList();

    List<CategoryVo> listAllCategory();

}
