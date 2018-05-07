package com.okdeer.mall.redis.redlock;

/**
 * 异常类
 * Created by Administrator on 2018/5/5 0005.
 */
public class UnableToAquireLockException extends RuntimeException {

    public UnableToAquireLockException() {
    }

    public UnableToAquireLockException(String message) {
        super(message);
    }

    public UnableToAquireLockException(String message, Throwable cause) {
        super(message, cause);
    }
}
