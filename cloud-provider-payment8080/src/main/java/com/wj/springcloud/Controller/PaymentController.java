package com.wj.springcloud.Controller;

import com.wj.springcloud.Service.PaymentService;
import com.wj.springcloud.entities.CommentResult;
import com.wj.springcloud.entities.Payment;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author ：actor
 * @date ：Created in 2021/6/14 10:33
 * @description：
 * @modified By：
 * @version: $
 */
@RestController
@Slf4j
public class PaymentController {

    @Autowired
    private PaymentService paymentService;

    @PostMapping(value = "/payment/create")
    public CommentResult create(Payment payment){

        int result = paymentService.create(payment);
        log.info("****插入数据库结果***"+result);

        if(result > 0){
            return  new CommentResult(200,"插入数据库成功",result);
        }else {
            return  new CommentResult(444,"插入数据库失败",null);
        }
    }


    @GetMapping(value = "/payment/getPaymentById")
    public CommentResult getPaymentById(@PathVariable("id") Long id){

        Payment payment = paymentService.getPaymentById(id);
        log.info("****查询数据库结果***"+payment);

        if(payment != null){
            return  new CommentResult(200,"查询数据库结果成功",payment);
        }else {
            return  new CommentResult(444,"查询数据库结果失败",null);
        }
    }

}
