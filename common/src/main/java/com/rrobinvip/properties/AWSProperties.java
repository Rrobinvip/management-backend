package com.rrobinvip.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "cloud.aws.credentials")
@Data
public class AWSProperties {
    private String accessKey;
    private String secretKey;
    private String bucketName;
    private String endPoint;
}
