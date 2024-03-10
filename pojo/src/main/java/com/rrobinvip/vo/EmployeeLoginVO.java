package com.rrobinvip.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(description = "Employee login return value")
public class EmployeeLoginVO implements Serializable {

    @ApiModelProperty("PK")
    private Long id;

    @ApiModelProperty("Username")
    private String userName;

    @ApiModelProperty("Name")
    private String name;

    @ApiModelProperty("JWT Token")
    private String token;

}
