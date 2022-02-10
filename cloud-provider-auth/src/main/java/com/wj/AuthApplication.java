package com.wj;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author ：actor
 * @date ：Created in 2021/7/26 14:36
 * @description：
 * @modified By：
 * @version: $
 */
@SpringBootApplication
@MapperScan("com.wj.springcloud.mapper")
public class AuthApplication {
    public static void main(String[] args) {
        SpringApplication.run(AuthApplication.class) ;
    }
}
