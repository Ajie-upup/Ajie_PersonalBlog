package com.ajie.model.request;

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
public class AddTagRequest {
    private String name;
    private String remark;
}
