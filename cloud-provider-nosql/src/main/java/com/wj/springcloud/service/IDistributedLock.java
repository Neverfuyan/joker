package com.wj.springcloud.service;

/**
 * @author ：actor
 * @date ：Created in 2022/2/9 14:49
 * @description：分布式锁
 * @modified By：
 * @version: $
 */
public interface IDistributedLock {

    /**
     * <h2>获取锁</h2>
     * */
    boolean acquire();
    /**
     * <h2>释放锁</h2>
     * */
    void release();
}
