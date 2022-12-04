package com.ajie.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author: ajie
 * @Date: 2022/12/3
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TagListDto {
    private Long id;
    /**
     * 标签名
     */
    private String name;
    /**
     * 备注
     */
    private String remark;
}
