package com.rrobinvip.vo;

import com.rrobinvip.entity.ComboItems;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ComboVO implements Serializable {

    private Long id;

    // Category ID
    private Long categoryId;

    // Combo name
    private String name;

    // Combo price
    private BigDecimal price;

    // Status: 0 for disabled, 1 for enabled
    private Integer status;

    // Description
    private String description;

    // Image
    private String image;

    // Update time
    private LocalDateTime updateTime;

    // Category name
    private String categoryName;

    // Association between combo and dishes
    private List<ComboItems> ComboItems = new ArrayList<>();
}

