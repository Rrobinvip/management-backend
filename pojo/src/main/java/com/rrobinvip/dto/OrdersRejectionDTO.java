package com.rrobinvip.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class OrdersRejectionDTO implements Serializable {

    private Long id;

    // Order reject reason
    private String rejectionReason;

}
