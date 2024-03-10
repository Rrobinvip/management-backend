package com.rrobinvip.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class PasswordEditDTO implements Serializable {

    // Employee id
    private Long empId;

    // Previous pw
    private String oldPassword;

    // New pw
    private String newPassword;

}
