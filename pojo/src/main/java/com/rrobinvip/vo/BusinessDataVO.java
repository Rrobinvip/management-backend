package com.rrobinvip.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * Value object
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BusinessDataVO implements Serializable {

    private Double turnover;// Turnover

    private Integer validOrderCount;// Number of valid orders

    private Double orderCompletionRate;// Order completion rate

    private Double unitPrice;// Average customer transaction price

    private Integer newUsers;// Number of new users

}

