package com.rrobinvip.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;
import java.math.BigDecimal;

/**
 * Combo-items relation
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ComboItems implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    private Long id;

    private Long comboId;

    private Long itemId;

    private String name;

    private BigDecimal price;

    private Integer copies;
}
