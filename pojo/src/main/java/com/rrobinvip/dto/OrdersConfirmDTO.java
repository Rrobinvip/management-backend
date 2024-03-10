package com.rrobinvip.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class OrdersConfirmDTO implements Serializable {

    private Long id;
    // Order status, 1-waiting payment, 2-waiting accepting, 3-accepted, 4-shipping, 5-finished, 6-canceled, 70refunded.
    private Integer status;

}
