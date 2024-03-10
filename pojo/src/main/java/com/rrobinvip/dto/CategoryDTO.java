package com.rrobinvip.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class CategoryDTO implements Serializable {

    // PK
    private Long id;

    // 1-item, 2-combo
    private Integer type;

    // name
    private String name;

    // order
    private Integer sort;

}
