package com.ajie.utils;

import org.springframework.beans.BeanUtils;

import java.util.List;
import java.util.stream.Collectors;

public class BeanCopyUtils {

    private BeanCopyUtils() {
    }

    public static <T> T copyBean(Object source,Class<T> clazz) {
        //创建目标对象
        T result = null;
        try {
            result = clazz.newInstance();
            //实现属性copy
            BeanUtils.copyProperties(source, result);
        } catch (Exception e) {
            e.printStackTrace();
        }
        //返回结果
        return result;
    }
    public static <R,T> List<T> copyBeanList(List<R> list, Class<T> clazz){
        return list.stream()
                .map(one -> copyBean(one, clazz))
                .collect(Collectors.toList());
    }
}