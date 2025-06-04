package com.yy.common;

import com.alibaba.fastjson.JSON;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

@RestControllerAdvice
public class GlobalResponseHandler implements ResponseBodyAdvice<Object> {
    @Override
    public boolean supports(MethodParameter returnType, Class converterType) {
        // 排除已封装或无需封装的情况（如String类型）
        return !returnType.getParameterType().equals(Result.class);
    }

    @Override
    public Object beforeBodyWrite(Object body, MethodParameter returnType,
                                  MediaType mediaType, Class converterType,
                                  ServerHttpRequest request, ServerHttpResponse response) {
        // 1. 异常结果直接返回
        if (body instanceof Result) {
            return body;
        }
        // 2. 处理String类型
        if (body instanceof String ||
                (body == null && returnType.getParameterType().equals(String.class))) {
            return JSON.toJSONString(Result.success(body));
        }
        // 统一封装为Result对象
        return Result.success(body);
    }
}