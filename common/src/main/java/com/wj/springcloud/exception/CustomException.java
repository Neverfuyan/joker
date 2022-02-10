package com.wj.springcloud.exception;

import lombok.Data;

/**
 * @author ：actor
 * @date ：Created in 2021/7/29 15:06
 * @description：自定义异常
 * @modified By：
 * @version: $
 */
@Data
public class CustomException extends RuntimeException {

    private static final long serialVersionUID = 9999999;

    private int code;

    public CustomException(){
        super();
    }

    public CustomException(int code, String message) {
        super(message);
        this.setCode(code);
    }

}
