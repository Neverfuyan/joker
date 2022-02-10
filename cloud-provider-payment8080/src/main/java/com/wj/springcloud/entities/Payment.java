package com.wj.springcloud.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author ：actor
 * @date ：Created in 2021/6/14 10:05
 * @description：订单
 * @modified By：
 * @version: 1.0.0$
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Payment {


    private  Long id;

    private String serial;
}
