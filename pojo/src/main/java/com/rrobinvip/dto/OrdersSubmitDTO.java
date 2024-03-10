package com.rrobinvip.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
public class OrdersSubmitDTO implements Serializable {
    // Address id
    private Long addressBookId;
    // pay method
    private int payMethod;
    // note
    private String remark;
    // Predicted deliver time
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime estimatedDeliveryTime;
    // deliver status, 1-out now, 0-other time
    private Integer deliveryStatus;

    private Integer tablewareNumber;
    private Integer tablewareStatus;
    // Package fee
    private Integer packAmount;
    // Total amount
    private BigDecimal amount;
}
