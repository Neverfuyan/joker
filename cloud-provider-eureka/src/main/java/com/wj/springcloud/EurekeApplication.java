package com.wj.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/**
 * @author ：actor
 * @date ：Created in 2021/7/26 14:52
 * @description：
 * @modified By：
 * @version: $
 */
@SpringBootApplication
@EnableEurekaServer
public class EurekeApplication {

    public static void main(String[] args) {
        SpringApplication.run(EurekeApplication.class);
    }
}
