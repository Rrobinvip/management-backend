package com.rrobinvip.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrderReportVO implements Serializable {

    // Dates, separated by commas, for example: 2022-10-01,2022-10-02,2022-10-03
    private String dateList;

    // Daily order count, separated by commas, for example: 260,210,215
    private String orderCountList;

    // Daily count of valid orders, separated by commas, for example: 20,21,10
    private String validOrderCountList;

    // Total number of orders
    private Integer totalOrderCount;

    // Number of valid orders
    private Integer validOrderCount;

    // Order completion rate
    private Double orderCompletionRate;

}
