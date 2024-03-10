package com.rrobinvip.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 套餐总览
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ComboOverViewVO implements Serializable {
    // Number of combos started
    private Integer sold;

    // Number of combos discontinued
    private Integer discontinued;
}
