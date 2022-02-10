package com.wj.springcloud.schedule;

import com.xxl.job.core.handler.IJobHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author ：actor
 * @date ：Created in 2022/1/24 13:52
 * @description：调度测试任务
 * @modified By：
 * @version: $
 */

public class JobHandleDemo extends IJobHandler {

    /** logger */
    private static final Logger logger = LoggerFactory.getLogger(JobHandleDemo.class);

    @Override
    public void execute() throws Exception {
        System.out.println("垃圾xxl.job");
    }
}
