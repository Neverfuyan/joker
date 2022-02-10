package com.wj.springcloud.Service;

import com.wj.springcloud.entities.Payment;
import org.apache.ibatis.annotations.Param;

/**
 * @author ：actor
 * @date ：Created in 2021/6/14 10:26
 * @description：
 * @modified By：
 * @version: $
 */
public interface PaymentService {

     int create(Payment payment);


     Payment getPaymentById(Long id);

}
