package com.yy.common;

import lombok.Data;

import java.time.Instant;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

@Data
public class Result<T> {
    private Integer code;  // 业务状态码（非HTTP状态码）
    private String msg;   // 提示信息
    private T data;       // 业务数据
    private String taskId;
    private String timestamp = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS")
            .withZone(ZoneId.of("GMT+8"))
            .format(Instant.ofEpochMilli(System.currentTimeMillis())); // 响应时间戳

    // 成功响应（带数据）
    public static <T> Result<T> success(T data) {
        Result<T> result = new Result<>();
        result.setCode(200);
        result.setMsg("操作成功");
        result.setData(data);
        result.setTaskId(BaseStorage.getTaskId());
        return result;
    }

    // 失败响应
    public static <T> Result<T> error(String msg) {
        Result<T> result = new Result<>();
        result.setCode(500);
        result.setMsg(msg);
        result.setTaskId(BaseStorage.getTaskId());
        return result;
    }
    public static <T> Result<T> error(Integer code,String msg) {
        Result<T> result = new Result<>();
        result.setTaskId(BaseStorage.getTaskId());
        result.setCode(code);
        result.setMsg(msg);
        return result;
    }
}