package com.yy.common.exception;

/**
 * 替换描述
 *
 * @ClassName LockTimeoutException
 * @Author yangfeng
 * @Date 2025/8/22 16:38
 * @Version 1.0
 */
public class LockInterruptedException extends BaseException{
    public LockInterruptedException() {
        super();
    }

    public LockInterruptedException(String message) {
        super(message);
    }

    public LockInterruptedException(String message, Throwable cause) {
        super(message, cause);
    }

    public LockInterruptedException(Throwable cause) {
        super(cause);
    }

    protected LockInterruptedException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}