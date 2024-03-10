package com.rrobinvip.dto;

import com.rrobinvip.entity.ComboItems;
import lombok.Data;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Data
public class ComboDTO implements Serializable {

    private Long id;

    // Category id
    private Long categoryId;

    // combo name
    private String name;

    // combo price
    private BigDecimal price;

    // 1-enable, 0-disable
    private Integer status;

    // description
    private String description;

    // image
    private String image;

    // Contains items
    private List<ComboItems> comboItems = new ArrayList<>();

}
