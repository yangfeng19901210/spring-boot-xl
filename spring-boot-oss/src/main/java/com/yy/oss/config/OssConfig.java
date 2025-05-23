package com.yy.oss.config;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.Resource;

@Configuration
public class OssConfig {
    @Value("${aliyun.oss.endpoint}")
    private String endpoint;
    @Resource
    private OssSecretConfig ossSecretConfig;

    @Bean
    public OSS ossClient() {
        return new OSSClientBuilder().build(endpoint, ossSecretConfig.getOssId(), ossSecretConfig.getOssSecret());
    }
}