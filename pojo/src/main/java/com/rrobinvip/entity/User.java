package com.rrobinvip.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class User implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    private Long id;

    // login unique ID
    private String openid;

    private String name;

    private String phone;

    private String sex;

    private String idNumber;

    private String avatar;

    private LocalDateTime createTime;
}
