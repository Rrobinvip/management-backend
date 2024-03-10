package com.rrobinvip.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class ComboPageQueryDTO implements Serializable {

    private int page;

    private int pageSize;

    private String name;

    // category id
    private Integer categoryId;

    // 1-enable, 2-disable
    private Integer status;

}
