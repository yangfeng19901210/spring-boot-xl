package com.yy.oss.config;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource(value = "classpath:secret/oss-secret.properties", encoding = "UTF-8")
@Data
public class OssSecretConfig {
    @Value("${oss.key.id}")
    private String ossId;
    @Value("${oss.key.secret}")
    private String ossSecret;

}