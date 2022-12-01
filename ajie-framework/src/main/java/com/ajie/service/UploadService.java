package com.ajie.service;

import com.ajie.common.ResponseResult;
import org.springframework.web.multipart.MultipartFile;

/**
 * @Author: ajie
 * @Date: 2022/12/1
 */
public interface UploadService {

    ResponseResult uploadImg(MultipartFile img);
}
