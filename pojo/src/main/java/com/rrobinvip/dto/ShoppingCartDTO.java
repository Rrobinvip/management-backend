package com.rrobinvip.dto;

import lombok.Data;
import java.io.Serializable;

@Data
public class ShoppingCartDTO implements Serializable {

    private Long ItemId;
    private Long ComboId;
    private String dishFlavor;

}
