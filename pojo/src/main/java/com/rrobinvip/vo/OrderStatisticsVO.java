package com.rrobinvip.vo;

import lombok.Data;
import java.io.Serializable;

@Data
public class OrderStatisticsVO implements Serializable {
    // Number of orders to be confirmed
    private Integer toBeConfirmed;

    // Number of orders confirmed
    private Integer confirmed;

    // Number of orders in delivery
    private Integer deliveryInProgress;
}
