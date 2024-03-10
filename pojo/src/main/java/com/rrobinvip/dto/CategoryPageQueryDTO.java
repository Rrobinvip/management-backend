package com.rrobinvip.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class CategoryPageQueryDTO implements Serializable {

    // page
    private int page;

    // page size
    private int pageSize;

    // name
    private String name;

    // 1-item, 2-combo
    private Integer type;

}
