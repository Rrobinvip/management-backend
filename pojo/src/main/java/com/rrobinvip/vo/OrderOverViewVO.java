package com.rrobinvip.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * Orders overview
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrderOverViewVO implements Serializable {
    // Number of orders waiting to be accepted
    private Integer waitingOrders;

    // Number of orders to be delivered
    private Integer deliveredOrders;

    // Number of orders completed
    private Integer completedOrders;

    // Number of orders cancelled
    private Integer cancelledOrders;

    // All orders
    private Integer allOrders;
}

