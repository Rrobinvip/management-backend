package com.rrobinvip.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;

/**
 * Address book
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AddressBook implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    private Long id;

    // User id
    private Long userId;

    // reception
    private String consignee;

    // phone number
    private String phone;

    // 1-man, 0-woman
    private String sex;

    // province name
    private String provinceName;

    // city name
    private String cityName;

    // detail address
    private String detail;

    // label
    private String label;

    // 1-true, 0-false
    private Integer isDefault;
}
