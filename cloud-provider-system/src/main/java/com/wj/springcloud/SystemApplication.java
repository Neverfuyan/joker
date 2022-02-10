package com.wj.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @author ：actor
 * @date ：Created in 2022/1/21 14:58
 * @description：基础服务启动类
 * @modified By：
 * @version: $
 */
@EnableEurekaClient
@EnableFeignClients
@SpringBootApplication(scanBasePackages = {"com.wj"})
public class SystemApplication {

    public static void main(String[] args) {
        SpringApplication.run(SystemApplication.class,args);
    }
}
