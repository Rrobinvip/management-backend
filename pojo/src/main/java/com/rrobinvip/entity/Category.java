package com.rrobinvip.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Category implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    private Long id;

    // 1-items, 2-combo
    private Integer type;

    // name
    private String name;

    // order
    private Integer sort;

    // 1-enable, 2-disable
    private Integer status;

    private LocalDateTime createTime;

    private LocalDateTime updateTime;

    private Long createUser;

    private Long updateUser;
}
