package com.yy.common;

import com.alibaba.ttl.TransmittableThreadLocal;
import com.yy.config.BaseConstant;

import java.util.LinkedHashMap;
import java.util.Map;

/*********************************************************
 **
 ** <br><br>
 ** @ClassName: BaseStorage
 ** @author: yangfeng
 ** @date: 2025/6/3 10:40
 ** @version: 1.0.0
 *********************************************************/
public class BaseStorage {
    private static ThreadLocal<Map<String, String>> threadLocal = new TransmittableThreadLocal<>();
    public static void pushTaskId(String taskId) {
        push(BaseConstant.TASK_ID, taskId);
    }

    public static String getTaskId() {
        return get(BaseConstant.TASK_ID);
    }
    public static void push(String key, String value) {
        Map<String, String> map = threadLocal.get();
        if (map == null) {
            map = new LinkedHashMap<>();
            threadLocal.set(map);
        }
        map.put(key, value);
    }
    public static String get(String key) {
        Map<String, String> map = threadLocal.get();
        if (map == null) {
            map = new LinkedHashMap<>();
            threadLocal.set(map);
        }
        return map.get(key);
    }
    public static void remove() {
        threadLocal.remove();
    }
}
