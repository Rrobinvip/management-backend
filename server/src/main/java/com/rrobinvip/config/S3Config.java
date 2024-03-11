package com.rrobinvip.config;

import com.rrobinvip.properties.AWSProperties;
import com.rrobinvip.utils.S3Util;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Configuration class for Amazon S3 utilities.
 * It leverages Spring's @Configuration to declare that it contains one or more @Bean methods
 * and may be processed by the Spring container to generate bean definitions and service requests
 * for those beans at runtime. This class specifically configures the S3Util bean.
 */
@Configuration
@Slf4j
public class S3Config {
    /**
     * Creates an S3Util bean if one does not already exist.
     * Utilizes AWSProperties to extract necessary configurations for S3 access.
     *
     * @param awsProperties An instance of AWSProperties containing accessKey, secretKey,
     *                      bucketName, and endPoint required for S3Util.
     * @return A new instance of S3Util configured with the provided AWSProperties.
     */
    @Bean
    @ConditionalOnMissingBean
    public S3Util s3Util(AWSProperties awsProperties) {
        log.info("Start creating S3 util..");
        return new S3Util(awsProperties.getAccessKey(),
                awsProperties.getSecretKey(),
                awsProperties.getBucketName(),
                awsProperties.getEndPoint());
    }
}
