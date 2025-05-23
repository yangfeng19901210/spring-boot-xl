package com.yy.oss.controller;

import com.yy.oss.utils.OssUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Map;

/*********************************************************
 ** 文件上传controller
 ** <br><br>
 ** @ClassName: FileController
 ** @author: yangfeng
 ** @date: 2025/5/21 15:02
 ** @version: 1.0.0
 *********************************************************/
@RequestMapping("/file")
@RestController
@RequiredArgsConstructor
@Slf4j
public class FileController {
    private final OssUtil ossUtil;
    @PostMapping("/upload")
    public ResponseEntity<Map<String, Object>> upload(@RequestParam("file") MultipartFile file) {
        try {
            String url = ossUtil.upload(file);
            return ResponseEntity.ok(Map.of(
                    "code", 200,
                    "url", url,
                    "message", "上传成功"
            ));
        } catch (Exception e) {
            return ResponseEntity.status(500).body(Map.of(
                    "code", 500,
                    "message", "上传失败：" + e.getMessage()
            ));
        }
    }
    /**
     * 删除文件
     * @param objectKey
     * @Return: org.springframework.http.ResponseEntity<?>
     * @author: yangfeng
     * @date: 2025/5/21 15:59
     **/
    @DeleteMapping("/deleteFile")
    public ResponseEntity<?> deleteFile(@RequestParam String objectKey) {
        try {
            ossUtil.deleteFile(objectKey);
            return ResponseEntity.ok(Map.of("code", 200, "msg", "删除成功"));
        } catch (Exception e) {
            return ResponseEntity.status(500)
                    .body(Map.of("code", 500, "msg", "删除失败：" + e.getMessage()));
        }
    }

    @PostMapping("/shardUpload")
    public ResponseEntity<Map<String, Object>> shardUpload(@RequestParam("file") MultipartFile file) {
        try {
            String url = ossUtil.shardUpload(file);
            return ResponseEntity.ok(Map.of(
                    "code", 200,
                    "url", url,
                    "message", "上传成功"
            ));
        } catch (Exception e) {
            return ResponseEntity.status(500).body(Map.of(
                    "code", 500,
                    "message", "上传失败：" + e.getMessage()
            ));
        }
    }
}
