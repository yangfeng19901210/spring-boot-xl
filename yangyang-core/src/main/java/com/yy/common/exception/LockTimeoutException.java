package com.yy.common.exception;

/**
 * 替换描述
 *
 * @ClassName LockTimeoutException
 * @Author yangfeng
 * @Date 2025/8/22 16:38
 * @Version 1.0
 */
public class LockTimeoutException extends BaseException{
    public LockTimeoutException() {
        super();
    }

    public LockTimeoutException(String message) {
        super(message);
    }

    public LockTimeoutException(String message, Throwable cause) {
        super(message, cause);
    }

    public LockTimeoutException(Throwable cause) {
        super(cause);
    }

    protected LockTimeoutException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}