package com.wj.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * @author ：actor
 * @date ：Created in 2022/1/24 9:10
 * @description：任务调度启动类
 * @modified By：
 * @version: $
 */
@SpringBootApplication(scanBasePackages = {"com.wj"})
@EnableEurekaClient
public class JobApplication {

    public static void main(String[] args) {
        SpringApplication.run(JobApplication.class,args);
    }
}
