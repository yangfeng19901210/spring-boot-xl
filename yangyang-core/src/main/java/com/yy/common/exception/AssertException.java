package com.yy.common.exception;

/*********************************************************
 **
 ** <br><br>
 ** @ClassName: AssertException
 ** @author: yangfeng
 ** @date: 2025/6/3 10:51
 ** @version: 1.0.0
 *********************************************************/
public class AssertException extends BaseException{
    public AssertException() {
        super();
    }

    public AssertException(String message) {
        super(message);
    }

    public AssertException(String message, Throwable cause) {
        super(message, cause);
    }

    public AssertException(Throwable cause) {
        super(cause);
    }

    protected AssertException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
