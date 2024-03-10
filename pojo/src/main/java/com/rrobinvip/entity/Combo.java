package com.rrobinvip.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * Combo
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Combo implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    private Long id;

    private Long categoryId;

    private String name;

    private BigDecimal price;

    // 1-enable, 2-disable
    private Integer status;

    private String description;

    private String image;

    private LocalDateTime createTime;

    private LocalDateTime updateTime;

    private Long createUser;

    private Long updateUser;
}
