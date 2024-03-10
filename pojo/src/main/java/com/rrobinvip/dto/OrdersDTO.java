package com.rrobinvip.dto;

import com.rrobinvip.entity.OrderDetail;
import lombok.Data;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Data
public class OrdersDTO implements Serializable {

    private Long id;

    // Order number
    private String number;

    // Order status, 1-waiting payment, 2-waiting shipment, 3-delivered, 4-finished, 5-canceled.
    private Integer status;

    // User id
    private Long userId;

    // Address id
    private Long addressBookId;

    // Order time
    private LocalDateTime orderTime;

    // Pay time
    private LocalDateTime checkoutTime;

    // Pay method
    private Integer payMethod;

    // Amount
    private BigDecimal amount;

    // note
    private String remark;

    // userName
    private String userName;

    // phone number
    private String phone;

    // address
    private String address;

    // reception
    private String consignee;

    private List<OrderDetail> orderDetails;

}
