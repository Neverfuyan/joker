package com.wj.springcloud.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author ：actor
 * @date ：Created in 2021/6/14 10:20
 * @description：
 * @modified By：
 * @version: $
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CommentResult<T> {

    private Integer code;

    private String message;

    private T data;


    private CommentResult(Integer code ,String message){
        this(code,message,null);
    }
}
