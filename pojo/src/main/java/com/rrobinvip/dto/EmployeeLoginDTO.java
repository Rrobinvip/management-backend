package com.rrobinvip.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

@Data
@ApiModel(description = "Employee Login Data Transfer Model")
public class EmployeeLoginDTO implements Serializable {

    @ApiModelProperty("Username")
    private String username;

    @ApiModelProperty("Password")
    private String password;

}
