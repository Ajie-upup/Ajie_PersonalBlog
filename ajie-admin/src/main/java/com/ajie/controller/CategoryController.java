package com.ajie.controller;

import com.ajie.common.ResponseResult;
import com.ajie.model.vo.CategoryVo;
import com.ajie.service.CategoryService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Author: ajie
 * @Date: 2022/12/3
 */
@RestController
@RequestMapping("/content/category")
public class CategoryController {

    @Resource
    private CategoryService categoryService;

    @GetMapping("/listAllCategory")
    public ResponseResult listAllCategory() {
        List<CategoryVo> list = categoryService.listAllCategory();
        return ResponseResult.okResult(list);
    }

//    @PreAuthorize("@ps.hasPermission('content:category:export')")
//    @GetMapping("/export")
//    public void export(HttpServletResponse response) {
//        try {
//            //设置下载文件的请求头
//            WebUtils.setDownLoadHeader("Category分类.xlsx", response);
//            //获取需要导出的数据
//            List<Category> categorys = categoryService.list();
//
//            List<ExcelCategoryVo> excelCategoryVos = BeanCopyUtils.copyBeanList(categorys, ExcelCategoryVo.class);
//            //把数据写入到Excel中
//            EasyExcel.write(response.getOutputStream(), ExcelCategoryVo.class).autoCloseStream(Boolean.FALSE).sheet("分类导出")
//                    .doWrite(excelCategoryVos);
//        } catch (Exception e) {
//            //如果出现异常也要响应json
//            ResponseResult result = ResponseResult.errorResult(AppHttpCodeEnum.SYSTEM_ERROR);
//            WebUtils.renderString(response, JSON.toJSONString(result));
//        }
//    }


}
