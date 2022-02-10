package com.wj.springcloud.Service.impl;

import com.wj.springcloud.Service.PaymentService;
import com.wj.springcloud.dao.PaymentDao;
import com.wj.springcloud.entities.Payment;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author ：actor
 * @date ：Created in 2021/6/14 10:26
 * @description：
 * @modified By：
 * @version: $
 */
@Service
public class PaymentServiceImpl implements PaymentService {

    @Resource
    private PaymentDao paymentDao;

    @Override
    public int create(Payment payment) {
        return paymentDao.create(payment);
    }

    @Override
    public Payment getPaymentById(Long id) {
        return paymentDao.getPaymentById(id);
    }
}
