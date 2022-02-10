package com.wj.springcloud.controller;

import com.alibaba.fastjson.JSONArray;
import com.wj.springcloud.constant.REnum;
import com.wj.springcloud.mq.Producter;
import com.wj.springcloud.pojo.Car;
import com.wj.springcloud.result.R;
import com.wj.springcloud.service.UserService;
import com.wj.springcloud.utils.EasyExcelUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author ：actor
 * @date ：Created in 2021/7/28 16:28
 * @description：资源服务器访问层
 * @modified By：
 * @version: $
 */
@Api("资源服务器访问层")
@RestController
@RequestMapping("resource")
public class ResourceController {

    @Autowired
    private UserService userService;

    @Autowired
    private Producter producter;

    @ApiOperation(value = "获取资源", tags = "获取资源")
    @GetMapping("/getResources")
    @ResponseBody
    public R getResources(@RequestBody JSONArray  jsonArray){
        return R.successOk(jsonArray);
    }

    @RequestMapping("/getResources1")
    public R getResources1(){
        producter.sendMessage();
        return R.successOk();
    }

    @RequestMapping("/getResources2")
    public R getResources2(){
        producter.sendMessage("process_exchange","process_routing","死信队列");
        return R.successOk();
    }


    @RequestMapping("/upload")
    public R getResources( MultipartFile file) throws Exception {
        ArrayList<? extends Car> cars = EasyExcelUtil.readerExcel(file, new Car().getClass());
        System.out.println(cars);
        return  R.successOk(REnum.SUCCESS);
    }


    @RequestMapping("/import")
    public R getResources(HttpServletResponse response, HttpServletRequest request) throws Exception {
        List<Car> cars = Arrays.asList(new Car("A12345"),
                new Car("B89201"));
        List<String> header = Arrays.asList("车牌号");
        EasyExcelUtil.writerExcel("车牌号",response,request,cars,Car.class,header);
        return  R.successOk(REnum.SUCCESS);
    }




}
