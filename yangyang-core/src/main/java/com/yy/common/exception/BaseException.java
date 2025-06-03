package com.yy.common.exception;

/*********************************************************
 ** 设置基础异常类，后期进行判断分析就可以使用了。
 ** <br><br>
 ** Date: Created in 2022/6/20 21:02
 ** @author loulan
 ** @version 0.0.0
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
