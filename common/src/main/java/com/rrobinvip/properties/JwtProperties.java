package com.rrobinvip.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "employee.jwt")
@Data
public class JwtProperties {

    /**
     * Manage employee JWT
     */
    private String adminSecretKey;
    private long adminTtl;
    private String adminTokenName;

    /**
     * User JWT
     */
    private String userSecretKey;
    private long userTtl;
    private String userTokenName;

}
