package com.yy.common.exception;

/*********************************************************
 ** 业务异常类
 ** <br><br>
 ** Date: Created in 2022/6/20 21:08
 ** @author loulan
 ** @version 0.0.0
 *********************************************************/
public class BusinessException extends BaseException{
    public BusinessException() {
        super();
    }

    public BusinessException(String message) {
        super(message);
    }

    public BusinessException(String message, Throwable cause) {
        super(message, cause);
    }

    public BusinessException(Throwable cause) {
        super(cause);
    }

    protected BusinessException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
