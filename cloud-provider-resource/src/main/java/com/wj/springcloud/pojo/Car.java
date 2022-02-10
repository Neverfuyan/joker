package com.wj.springcloud.pojo;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author ：actor
 * @date ：Created in 2021/8/10 8:14
 * @description：
 * @modified By：
 * @version: $
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Car {

    @ExcelProperty(value = "车牌号",index = 3)
    private String number;
}
