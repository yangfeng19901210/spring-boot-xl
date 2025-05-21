package com.yy.oss.utils;

import com.aliyun.oss.OSS;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Optional;
import java.util.UUID;

@Component
@Slf4j
public class OssUtil {
    private final OSS ossClient;
    @Value("${aliyun.oss.bucket-name}")
    private String bucketName;
    @Value("${aliyun.oss.dir-prefix}")
    private String dirPrefix;
    @Value("${aliyun.oss.endpoint}")
    private String endpoint;

    public OssUtil(OSS ossClient) {
        this.ossClient = ossClient;
    }

    public String upload(MultipartFile file) {
        try {
            // 1. 生成唯一文件名
            String originalName = file.getOriginalFilename();
            String fileName = UUID.randomUUID() + getFileExtension(originalName);
            
            // 2. 生成存储路径（格式：uploads/2025/05/21/uuid.jpg）
            String datePath = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyyMMdd"));
            String objectKey = dirPrefix + datePath + "/" + fileName;

            // 3. 上传文件流
            try (InputStream inputStream = file.getInputStream()) {
                ossClient.putObject(bucketName, objectKey, inputStream);
            }
            
            // 4. 返回访问地址
            return "https://" + bucketName + "." + endpoint + "/" + objectKey;
        } catch (IOException e) {
            throw new RuntimeException("文件上传失败", e);
        }
    }

    // 获取文件扩展名
    private String getFileExtension(String fileName) {
        return Optional.ofNullable(fileName)
                .filter(f -> f.contains("."))
                .map(f -> f.substring(f.lastIndexOf(".")))
                .orElse(".dat");
    }
    /**
     * 删除文件
     * @param objectKey
     * @Return: java.lang.Boolean
     * @author: yangfeng
     * @date: 2025/5/21 15:55
     **/
    public Boolean deleteFile(String objectKey) {
        try {
            boolean isExist = ossClient.doesObjectExist(bucketName, objectKey);
            if (!isExist) {
                // 如果文件不存在就直接返回true。不需要进行删除
                return Boolean.TRUE;
            }

            // 删除文件或目录。如果要删除目录，目录必须为空。
            ossClient.deleteObject(bucketName, objectKey);

            return Boolean.TRUE;
        } catch (Exception ex) {
            log.error("oss文件删除失败【downloadFile】：" + objectKey);
            throw new RuntimeException("删除文件失败",ex);
        }
    }
}