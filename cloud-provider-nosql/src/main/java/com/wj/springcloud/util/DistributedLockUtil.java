package com.wj.springcloud.util;

/**
 * <h1>分布式锁工具类</h1>
 * @author actor
 */
public class DistributedLockUtil {

    /**
     * 获取分布式锁
     * 默认获取锁15s超时, 锁过期时间15s
     * @return
     */
    public static RedisLock getDistributedLock(String lockKey) {
        lockKey = assembleKey(lockKey);
        return new RedisLock(lockKey);
    }

    /**
     * 获取分布式锁
     * @return
     */
    public static RedisLock getDistributedLock(String lockKey, int timeoutMsecs) {
        lockKey = assembleKey(lockKey);
        return new RedisLock(lockKey, timeoutMsecs);
    }

    /**
     * 获取分布式锁
     * @return
     */
    public static RedisLock getDistributedLock(String lockKey, int timeoutMsecs, int expireMsecs) {
        lockKey = assembleKey(lockKey);
        return new RedisLock(lockKey, expireMsecs, timeoutMsecs);
    }

    /**
     * 对 key 进行拼接
     */
    private static String assembleKey(String lockKey) {
        return String.format("imooc_analyze_%s", lockKey);
    }
}
