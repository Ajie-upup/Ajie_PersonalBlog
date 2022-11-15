package com.ajie.exception;

import com.ajie.common.AppHttpCodeEnum;

/**
 * 自定义系统异常
 *
 * @Author: ajie
 * @Date: 2022/11/15
 */
public class SystemException extends RuntimeException {
    private int code;

    private String msg;

    public int getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }

    public SystemException(AppHttpCodeEnum httpCodeEnum) {
        super(httpCodeEnum.getMsg());
        this.code = httpCodeEnum.getCode();
        this.msg = httpCodeEnum.getMsg();
    }
}
