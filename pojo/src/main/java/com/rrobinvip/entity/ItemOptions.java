package com.rrobinvip.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;

/**
 * Item options
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ItemOptions implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    private Long id;
    private Long itemId;
    private String name;
    private String value;

}
