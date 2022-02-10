package com.wj;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;




/**
 * @author ：actor
 * @date ：Created in 2021/7/26 14:33
 * @description：
 * @modified By：
 * @version: $
 */
@SpringBootApplication(scanBasePackages ={"com.wj"})
@EnableEurekaClient
@MapperScan("com.wj.springcloud.mapper")
public class ResourceApplication {

    public static void main(String[] args) {
        SpringApplication.run(ResourceApplication.class) ;
    }
}
