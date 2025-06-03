package com.yy.common.exception;

import com.yy.common.Result;
import io.gitee.loulan_yxq.owner.core.exception.AssertException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/*********************************************************
 ** 全局异常处理
 ** <br><br>
 ** Date: Created in 2022/6/27 15:56
 ** @author loulan
 ** @version 0.0.0
 *********************************************************/
@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {
    /**
     * 处理断言异常，并返回数据
     *
     * @param
     * @return
     * @throws
     * @author :loulan
     */
    @ExceptionHandler({IllegalArgumentException.class, AssertException.class,BusinessException.class, BaseException.class})
    public Result handlerAssertException(Exception ex) {
        log.error("发生业务异常", ex);
        return Result.error(ex.getMessage());
    }

    /**
     * 处理未知异常信息
     *
     * @param
     * @return
     * @throws
     * @author :loulan
     */
    @ExceptionHandler(Exception.class)
    public Result handlerException(Exception ex) {
        log.error("发生异常", ex);
        return Result.error("系统忙，请稍后重试");
    }
}
