package com.wj.springcloud.controller;

import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author ：actor
 * @date ：Created in 2022/1/21 15:07
 * @description：基础服务
 * @modified By：
 * @version: $
 */
@Api("基础服务")
@RestController
@RequestMapping("system")
public class SystemController {

    @GetMapping("getSystemInfo")
    public void getSystemInfo(){
        return;
    }

}
