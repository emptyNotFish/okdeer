package com.okdeer.mall.redis.lock;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Component;
import utils.StringUtils;

import java.util.concurrent.TimeUnit;

/**
 * 大概思路是用setNx（SET if Not eXists）命令和setEx(设置超时时间)配合使用。 setNx是一个耗时操作，因为它需要查询这个键是否存在，
 * 就算redis的百万的qps，在高并发的场景下，这种操作也是有问题的。关于redis实现分布式锁，redis官方推荐使用redlock。
 * Created by Administrator on 2018/5/5 0005.
 */
@Component
public class DistributedLockHandler {

    private static final Logger logger = LoggerFactory.getLogger(DistributedLockHandler.class);

    private static final long LOCK_EXPIRE = 30*1000L;//单个业务持有锁的时间30S，防止死锁

    private static final long LOCK_TRY_INTERVAL = 30L;//尝试间隔时间 默认30ms尝试一次

    private static final long LOCK_TRY_TIMEOUT = 20*1000L;//默认尝试超时时间20s

    @Autowired
    private StringRedisTemplate redisTemplate;

    /**
     * 尝试获取全局锁
     * @param lock 锁的名称
     * @return true 获取成功，false获取失败
     */
    public boolean tryLock(Lock lock){
        return getLock(lock,LOCK_TRY_TIMEOUT, LOCK_TRY_INTERVAL, LOCK_EXPIRE);

    }

    /**
     * 尝试获取全局锁
     * @param lock 锁的名称
     * @param timeout 获取锁的超时时间
     * @return true 获取成功，false获取失败
     */
    public boolean tryLock(Lock lock, long timeout){
        return getLock(lock,timeout, LOCK_TRY_INTERVAL, LOCK_EXPIRE);


    }

    /**
     * 尝试获取全局锁
     * @param lock 锁的名称
     * @param timeout 获取锁的超时时间
     * @param tryInterval 多少毫秒尝试获取一次
     * @return true 获取成功，false获取失败
     */
    public boolean tryLock(Lock lock, long timeout, long tryInterval){
        return getLock(lock,timeout, tryInterval, LOCK_EXPIRE);

    }

    /**
     * 尝试获取全局锁
     * @param lock 锁的名称
     * @param timeout 获取锁的超时时间
     * @param tryInterval 多少毫秒尝试获取一次
     * @param lockExpireTime 锁的过期时间
     * @return true 获取成功，false获取失败
     */
    public boolean tryLock(Lock lock, long timeout, long tryInterval, long lockExpireTime){
        return getLock(lock,timeout, tryInterval, lockExpireTime);

    }

    public boolean getLock(Lock lock, long timeout, long tryInterval, long lockExpireTime){
        try {

            if (StringUtils.isEmpty(lock.getName()) || StringUtils.isEmpty(lock.getValue())) {
                return  false;
            }

            long startTime = System.currentTimeMillis();
            do {
                if (!redisTemplate.hasKey(lock.getName())) {
                    ValueOperations<String, String> ops = redisTemplate.opsForValue();
                    ops.set(lock.getName(), lock.getValue(), lockExpireTime, TimeUnit.MILLISECONDS);
                    return true;
                }else {
                    //存在锁
                    logger.debug("lock is exist!!");
                }
                if (System.currentTimeMillis() - startTime > timeout ) {
                    // 尝试超过了设定值之后直接跳出循环
                    return false;
                }
                Thread.sleep(tryInterval);
            }while (redisTemplate.hasKey(lock.getName()));
        }catch (InterruptedException e) {
            logger.error(e.getMessage());
            return false;
        }
        return false;
    }
    /**
     * 释放锁
     * @param lock
     */
    public void releaseLock(Lock lock){
        if (!StringUtils.isEmpty(lock.getName())) {
            redisTemplate.delete(lock.getName());
        }

    }
}
