package com.wj.springcloud.resource;

import com.wj.springcloud.resource.impl.ResourceServiceImpl;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author ：actor
 * @date ：Created in 2021/7/28 16:24
 * @description：资源服务调用
 * @modified By：
 * @version: $
 */
@FeignClient(value = "resource1-server", fallback = ResourceServiceImpl.class)
public interface ResourceService {

    @RequestMapping(value = "/resource/getResources")
    String getResource();

}
