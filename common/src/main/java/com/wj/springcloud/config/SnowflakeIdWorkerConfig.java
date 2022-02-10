package com.wj.springcloud.config;

import com.wj.springcloud.utils.SnowflakeIdWorker;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author ：actor
 * @date ：Created in 2021/8/23 16:22
 * @description：雪花算法
 * @modified By：
 * @version: $
 */
@Configuration
public class SnowflakeIdWorkerConfig {

    @Bean
    public SnowflakeIdWorker idWorker() {
        return new SnowflakeIdWorker(0,0);
    }

}
