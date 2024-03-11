package com.rrobinvip.utils;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import software.amazon.awssdk.auth.credentials.AwsBasicCredentials;
import software.amazon.awssdk.auth.credentials.StaticCredentialsProvider;
import software.amazon.awssdk.awscore.exception.AwsServiceException;
import software.amazon.awssdk.core.exception.SdkClientException;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;
import software.amazon.awssdk.services.s3.model.S3Exception;

@Data
@AllArgsConstructor
@Slf4j
public class S3Util {
    private String accessKey;
    private String secretKey;
    private String bucketName;
    private String endPoint;

    /**
     * Handle bytes file content and upload it to S3 bucket.
     *
     * @param bytes    binary file content
     * @param fileName file name
     * @return accessing url
     */
    public String upload(byte[] bytes, String fileName) {
        AwsBasicCredentials credentials = AwsBasicCredentials.create(accessKey, secretKey);

        S3Client s3 = S3Client.builder()
                .credentialsProvider(StaticCredentialsProvider.create(credentials))
                .region(Region.US_EAST_1)
                .build();

        PutObjectRequest request = PutObjectRequest.builder()
                .bucket(bucketName)
                .key(fileName)
                .build();
        try {
            s3.putObject(request, RequestBody.fromBytes(bytes));
        } catch (AwsServiceException e) {
            log.error("An error response from an AWS service.");
            log.error("Error message: {}", e.awsErrorDetails());
            log.error("Error localized message: {}", e.getLocalizedMessage());
            log.error("Request ID: {}", e.requestId());
        } catch (SdkClientException e) {
            log.error("An error from sdk client");
            log.error("Error message: {}", e.getMessage());
            log.error("Error localized message: {}", e.getLocalizedMessage());
        }


        return String.format("%s%s", endPoint, fileName);
    }
}
