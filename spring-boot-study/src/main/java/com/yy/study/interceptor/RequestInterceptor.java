package com.yy.study.interceptor;

import com.yy.common.BaseStorage;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import java.util.UUID;

/*********************************************************
 ** 请求拦截器
 ** <br><br>
 ** @ClassName: RequestInterceptor
 ** @author: yangfeng
 ** @date: 2025/6/3 10:18
 ** @version: 1.0.0
 *********************************************************/
@Slf4j
public class RequestInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String taskId = UUID.randomUUID().toString().replace("-", "");
        BaseStorage.pushTaskId(taskId);
        return true;
    }
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
    }
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        BaseStorage.remove();
    }
}
