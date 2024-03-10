package com.rrobinvip.vo;

import com.rrobinvip.entity.OrderDetail;
import com.rrobinvip.entity.Orders;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.io.Serializable;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderVO extends Orders implements Serializable {

    // Order items information
    private String orderItems;

    // Order details
    private List<OrderDetail> orderDetailList;

}
