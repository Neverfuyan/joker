package com.wj.springcloud.controller;

import com.wj.springcloud.constant.REnum;
import com.wj.springcloud.result.R;
import com.wj.springcloud.util.RedisUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author ：actor
 * @date ：Created in 2021/8/16 9:37
 * @description：redis 远程调用接口
 * @modified By：
 * @version: $
 */
@RestController
@RequestMapping("redis")
@Slf4j
public class RedisController {

    @Autowired
    private RedisUtils redisUtils;

    /**
     * redis  新增字符存储
     * @param key 关键字
     * @param value 字符串存储值
     * @param expireTime 有效时间，单位秒
     * @return
     */
    @RequestMapping(value = "/setString", method = RequestMethod.POST)
    public R setString(String key, String value, Long expireTime) {
        try {
            redisUtils.set(key, value, expireTime);
        } catch (Exception e) {
            log.error("新增redis异常:",e);
            return R.successFail(REnum.EXCEPTION.getCode(), REnum.EXCEPTION.getDesc());
        }
        return R.successOk();
    }


    /**
     *  redis读取某个hash存储的单个key对应的值
     * @param key  hash存储的关键值
     * @param hashKey hash存储的hashKey
     * @return
     */
    @RequestMapping(value = "/getHash", method = RequestMethod.POST)
    public R getHash(String key, String hashKey) {
        try {
             redisUtils.hget(key, hashKey);
        } catch (Exception e) {
            log.error("获取hash redis异常:",e);
            return R.successFail(REnum.EXCEPTION.getCode(), REnum.EXCEPTION.getDesc());
        }
        return R.successOk();
    }


    /**
     * 新增hash 数据
     * @param key
     * @param hashKey
     * @param value
     * @param expireTime
     * @return
     */
    @RequestMapping(value = "/setHash")
    public R setHash(String key, String hashKey, String value, Long expireTime) {
        try {
            redisUtils.hset(key, hashKey, value, expireTime);
        } catch (Exception e) {
            log.error("存入hash redis异常:",e);
            return R.successFail(REnum.EXCEPTION.getCode(), REnum.EXCEPTION.getDesc());
        }
        return R.successOk();
    }


}
