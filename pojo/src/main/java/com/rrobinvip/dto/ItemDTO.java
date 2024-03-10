package com.rrobinvip.dto;

import com.rrobinvip.entity.ItemOptions;
import lombok.Data;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Data
public class ItemDTO implements Serializable {

    private Long id;
    // item name
    private String name;
    // item category id
    private Long categoryId;
    // item price
    private BigDecimal price;
    // image
    private String image;
    // description
    private String description;
    // 1-enable, 2-disable
    private Integer status;
    //口味
    private List<ItemOptions> flavors = new ArrayList<>();

}
