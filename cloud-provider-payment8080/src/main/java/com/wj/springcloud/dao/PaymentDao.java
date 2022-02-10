package com.wj.springcloud.dao;

import com.wj.springcloud.entities.Payment;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * @author ：actor
 * @date ：Created in 2021/6/14 10:08
 * @description：订到dao
 * @modified By：
 * @version: 1.0.0$
 */
@Mapper
public interface PaymentDao {

    public int create(Payment payment);


    public Payment getPaymentById(@Param("id")Long id);
}
