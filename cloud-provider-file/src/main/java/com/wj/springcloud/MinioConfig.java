package com.wj.springcloud;

import io.minio.MinioClient;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author ：actor
 * @date ：Created in 2022/2/23 11:30
 * @description：
 * @modified By：
 * @version: $
 */
@Configuration
@Data
@AllArgsConstructor
@NoArgsConstructor
@ConfigurationProperties(prefix = "minio")
public class MinioConfig {

     /**
      * minio地址
      */
     private String endpoint;

     /**
      * 账号
      */
     private String accessKey;

     /**
      * 密码
      */
     private String secretKey;


     @Bean
     public MinioClient minioClient(){
          return MinioClient.builder()
                  .endpoint(endpoint)
                  .credentials(accessKey, secretKey)
                  .build();
     }


}
