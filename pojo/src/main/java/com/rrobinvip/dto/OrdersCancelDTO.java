package com.rrobinvip.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class OrdersCancelDTO implements Serializable {

    private Long id;
    // Order cancel reason
    private String cancelReason;

}
