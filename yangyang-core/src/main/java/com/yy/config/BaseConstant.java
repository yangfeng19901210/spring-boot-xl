package com.yy.config;

/*********************************************************
 ** 常量数据
 ** <br><br>
 ** @ClassName: BaseConstant1
 ** @author: yangfeng
 ** @date: 2025/6/3 10:35
 ** @version: 1.0.0
 *********************************************************/
public interface BaseConstant {
    // 站点id
    String STATION_ID="stationId";

    //客户端id
    String CLIENT_ID = "clientId";

    // 客户端编码
    String CLIENT_CODE = "clientCode";

    // 客户端登录类型
    String CLIENT_GRANT_TYPE = "grantType";

    //用户id
    String USER_ID = "userId";

    // 用户名
    String USERNAME = "username";

    // 当前请求对sql操作的唯一性id（一个请求有多个sql操作，该id可以知道哪些sql是来自同一个请求的操作）.任务id
    String TASK_ID = "taskId";
}
