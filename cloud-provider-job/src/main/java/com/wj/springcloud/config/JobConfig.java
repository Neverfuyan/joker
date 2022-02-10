package com.wj.springcloud.config;

import com.xxl.job.core.executor.impl.XxlJobSpringExecutor;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author ：actor
 * @date ：Created in 2022/1/24 15:11
 * @description：调度任务配置中心
 * @modified By：
 * @version: $
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
@Configuration
public class JobConfig {

    /** logger */
    private static final Logger logger = LoggerFactory.getLogger(JobConfig.class);

    /**
     * xxl 注册地址
     */
    @Value("${xxl.job.admin.addresses}")
    private String adminAddresses;

    /**
     * 分组名称
     */
    @Value("${xxl.job.executor.appname}")
    private String appname;

    /**
     * ip
     */
    @Value("${xxl.job.executor.ip}")
    private String ip;

    /**
     * 端口
     */
    @Value("${xxl.job.executor.port}")
    private Integer port;

    /**
     * token 值
     */
    @Value("${xxl.job.accessToken}")
    private String accessToken;

    /**
     * 日志地址
     */
    @Value("${xxl.job.executor.logpath}")
    private String logPath;

    /**
     * 日志保存天数
     */
    @Value("${xxl.job.executor.logretentiondays}")
    private Integer logRetentionDays;


    @Bean
    public XxlJobSpringExecutor xxlJobExecutor() {
        logger.info(">>>>>>>>>>> xxl-job config init.");
        XxlJobSpringExecutor xxlJobSpringExecutor = new XxlJobSpringExecutor();
        xxlJobSpringExecutor.setAdminAddresses(adminAddresses);
        xxlJobSpringExecutor.setAppname(appname);
        xxlJobSpringExecutor.setIp(ip);
        xxlJobSpringExecutor.setPort(port);
        xxlJobSpringExecutor.setAccessToken(accessToken);
        xxlJobSpringExecutor.setLogPath(logPath);
        xxlJobSpringExecutor.setLogRetentionDays(logRetentionDays);
        return xxlJobSpringExecutor;
    }
}
