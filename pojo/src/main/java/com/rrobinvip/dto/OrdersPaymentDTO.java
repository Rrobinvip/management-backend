package com.rrobinvip.dto;

import lombok.Data;
import java.io.Serializable;

@Data
public class OrdersPaymentDTO implements Serializable {
    // Order number
    private String orderNumber;

    // Pay method
    private Integer payMethod;

}
