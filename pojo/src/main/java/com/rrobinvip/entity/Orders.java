package com.rrobinvip.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * Order
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Orders implements Serializable {

    // Order status, 1-waiting payment, 2-waiting shipment, 3-delivered, 4-finished, 5-canceled.
    public static final Integer PENDING_PAYMENT = 1;
    public static final Integer TO_BE_CONFIRMED = 2;
    public static final Integer CONFIRMED = 3;
    public static final Integer DELIVERY_IN_PROGRESS = 4;
    public static final Integer COMPLETED = 5;
    public static final Integer CANCELLED = 6;
    public static final Integer REFUNDED = 7;

    /**
     * 0-unpaid, 1-paid, 2-refunded
     */
    public static final Integer UN_PAID = 0;
    public static final Integer PAID = 1;
    public static final Integer REFUND = 2;

    @Serial
    private static final long serialVersionUID = 1L;

    private Long id;

    private String number;

    // Order status, 1-waiting payment, 2-waiting shipment, 3-delivered, 4-finished, 5-canceled.
    private Integer status;

    private Long userId;

    private Long addressBookId;

    private LocalDateTime orderTime;

    private LocalDateTime checkoutTime;

    private Integer payMethod;

    //支付状态 0-unpaid, 1-paid, 2-refunded
    private Integer payStatus;

    private BigDecimal amount;

    private String remark;

    private String userName;

    private String phone;

    private String address;

    private String consignee;

    private String cancelReason;

    private String rejectionReason;

    private LocalDateTime cancelTime;

    private LocalDateTime estimatedDeliveryTime;

    private Integer deliveryStatus;

    private LocalDateTime deliveryTime;

    private int packAmount;

}
