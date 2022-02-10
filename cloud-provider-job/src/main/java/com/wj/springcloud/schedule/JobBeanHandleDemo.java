package com.wj.springcloud.schedule;

import com.xxl.job.core.context.XxlJobContext;
import com.xxl.job.core.context.XxlJobHelper;
import com.xxl.job.core.handler.annotation.XxlJob;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * @author ：actor
 * @date ：Created in 2022/1/24 17:06
 * @description：Bean注册方式
 * @modified By：
 * @version: $
 */

@Component
public class JobBeanHandleDemo {

    /** logger */
    private static final Logger logger = LoggerFactory.getLogger(JobBeanHandleDemo.class);

    @XxlJob(value = "JobBeanHandleDemo")
    public void function(){
        XxlJobContext xxlJobContext = XxlJobContext.getXxlJobContext();
        String jobParam = xxlJobContext.getJobParam();
        System.out.println("传入参数:"+jobParam);
        int shardIndex = XxlJobHelper.getShardIndex();
        int shardTotal = XxlJobHelper.getShardTotal();
       logger.info("xxl开始,分片序号:【{}】,机器实例总数:【{}】",shardIndex,shardTotal);
    }
}
