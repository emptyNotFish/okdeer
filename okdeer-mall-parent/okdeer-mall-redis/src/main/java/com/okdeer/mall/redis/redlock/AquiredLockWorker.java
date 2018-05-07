package com.okdeer.mall.redis.redlock;

/**
 * redis官方推荐使用redlock
 * 获取锁后需要处理的逻辑
 * Created by Administrator on 2018/5/5 0005.
 */
public interface AquiredLockWorker<T> {
    T invokeAfterLockAquire() throws Exception;
}
