package com.rrobinvip.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "rrobinvip.wechat")
@Data
public class WeChatProperties {

    private String appid; // AppID of the mini program
    private String secret; // Secret key of the mini program
    private String mchid; // Merchant ID
    private String mchSerialNo; // Serial number of the merchant API certificate
    private String privateKeyFilePath; // Merchant private key file path
    private String apiV3Key; // Key for decrypting certificates
    private String weChatPayCertFilePath; // Platform certificate path
    private String notifyUrl; // Callback URL for successful payment
    private String refundNotifyUrl; // Callback URL for successful refund

}

