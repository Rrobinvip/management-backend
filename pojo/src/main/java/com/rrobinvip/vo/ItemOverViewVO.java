package com.rrobinvip.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * Items overview
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ItemOverViewVO implements Serializable {
    private Integer sold;

    private Integer discontinued;
}
