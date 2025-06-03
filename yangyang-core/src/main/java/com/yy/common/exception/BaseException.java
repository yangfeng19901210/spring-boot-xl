package com.yy.common.exception;

/*********************************************************
 ** 基础异常类
 ** <br><br>
 ** @ClassName: BaseException
 ** @author: yangfeng
 ** @date: 2025/6/3 10:50
 ** @version: 1.0.0
 *********************************************************/
public class BaseException extends RuntimeException{
    public BaseException() {
        super();
    }

    public BaseException(String message) {
        super(message);
    }

    public BaseException(String message, Throwable cause) {
        super(message, cause);
    }

    public BaseException(Throwable cause) {
        super(cause);
    }

    protected BaseException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

}
