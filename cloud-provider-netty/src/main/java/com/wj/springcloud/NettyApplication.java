package com.wj.springcloud;

import com.wj.springcloud.server.NettyServer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;


/**
 * @author ：actor
 * @date ：Created in 2022/3/9 9:39
 * @description：netty启动类
 * @modified By：
 * @version: $
 */
@SpringBootApplication(scanBasePackages = {"com.wj"})
@EnableEurekaClient
public class NettyApplication implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(NettyApplication.class,args);
    }

    @Autowired
    NettyServer nettyServer;

    @Override
    public void run(String... args) throws Exception {
        nettyServer.nettyServerStart();
    }
}
