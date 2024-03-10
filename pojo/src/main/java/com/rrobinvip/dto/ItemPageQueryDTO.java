package com.rrobinvip.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class ItemPageQueryDTO implements Serializable {

    private int page;

    private int pageSize;

    private String name;

    // category ID
    private Integer categoryId;

    // 1-enable, 2-disable
    private Integer status;

}
