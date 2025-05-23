package com.yy.oss.utils;

import com.aliyun.oss.ClientException;
import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSException;
import com.aliyun.oss.model.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
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
            String uniqueFileName = getUniqueFileName(file.getOriginalFilename());
            // 3. 上传文件流
            try (InputStream inputStream = file.getInputStream()) {
                ossClient.putObject(bucketName, uniqueFileName, inputStream);
            }
            
            // 4. 返回访问地址
            return "https://" + bucketName + "." + endpoint + "/" + uniqueFileName;
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
    /**
     * 针对大文件分片上传
     * @param file
     * @Return: java.lang.String
     * @author: yangfeng
     * @date: 2025/5/21 17:23
     **/
    public String shardUpload(MultipartFile file){
        String objectKey = getUniqueFileName(file.getOriginalFilename());
        InitiateMultipartUploadRequest initiateMultipartUploadRequest = new InitiateMultipartUploadRequest(bucketName, objectKey);
        String uploadId = ossClient.initiateMultipartUpload(initiateMultipartUploadRequest).getUploadId();
        log.info("分片上传的uploadId: {}", uploadId);
        try {
            long fileSize = file.getSize();
            long partSize = 5 * 1024 * 1024L; // 5MB分片[2](@ref)
            int partCount = (int) (fileSize / partSize);
            if (fileSize % partSize != 0) partCount++;
            // 3. 分片上传
            List<PartETag> partETags = new ArrayList<>();
            for (int i = 0; i < partCount; i++) {
                long startPos = partSize * i;
                long curPartSize = Math.min(partSize, fileSize - startPos);

                try (InputStream inputStream = file.getInputStream()) {
                    inputStream.skip(startPos);

                    UploadPartRequest uploadRequest = new UploadPartRequest();
                    uploadRequest.setBucketName(bucketName);
                    uploadRequest.setKey(objectKey);
                    uploadRequest.setUploadId(uploadId);
                    uploadRequest.setInputStream(inputStream);
                    uploadRequest.setPartSize(curPartSize);
                    uploadRequest.setPartNumber(i + 1);
                    UploadPartResult uploadResult = ossClient.uploadPart(uploadRequest);
                    partETags.add(uploadResult.getPartETag());
                    System.out.printf("分片 %d 上传完成（%d bytes）\n", i+1, curPartSize);
                }
            }

            // 4. 合并分片[3,5](@ref)
            CompleteMultipartUploadRequest completeRequest = new CompleteMultipartUploadRequest(
                    bucketName, objectKey, uploadId, partETags);
            CompleteMultipartUploadResult result = ossClient.completeMultipartUpload(completeRequest);
            String url = result.getLocation();
            log.info("文件上传完成，访问地址：{}", url);
            return url;
        } catch (OSSException | ClientException | IOException e) {
            ossClient.abortMultipartUpload(new AbortMultipartUploadRequest(bucketName, objectKey, uploadId));
            System.err.println("上传失败：" + e.getMessage());
        }
        return null;
    }

    public String getUniqueFileName(String originalName) {
        String fileName = UUID.randomUUID() + getFileExtension(originalName);

        // 2. 生成存储路径（格式：uploads/2025/05/21/uuid.jpg）
        String datePath = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyyMMdd"));
        String objectKey = dirPrefix + datePath + "/" + fileName;
        return objectKey;
    }

    public static void main(String[] args) {
        System.out.println(UUID.randomUUID());
    }
}