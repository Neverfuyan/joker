package com.wj.springcloud.result;


import com.wj.springcloud.constant.REnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author ：actor
 * @date ：Created in 2021/7/29 15:38
 * @description：全局返回
 * @modified By：
 * @version: $
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class R<T> implements Serializable {

    private static final long serialVersionUID = -2283416827691016207L;

    /**
     * 返回状态
     */
    private String code;

    /**
     *返回状态说明
     */
    private String message;

    /**
     * 返回结果参数
     */
    private T data;

    public R(String code, String message){
        this.code = code;
        this.message = message;
    }

    public R setData(T data){
        this.data = data;
        return this;
    }

    /**
     * 自定义返回，没有返回结果
     * @param code
     * @param message
     * @return
     */
    public static  R create(String code, String message){
        R r = new R(code,message);
        return r;
    }

    /**
     * 返回成功没有数据
     * @return
     */
    public static R successOk(){
        return new R(REnum.SUCCESS.getCode(),REnum.SUCCESS.getDesc());
    }

    /**
     * 返回成功并带上数据
     * @return
     */
    public static<T> R successOk(T data){
        return successOk().setData(data);
    }

    /**
     * 失败无参数返回
     * @return
     */
    public static R successFail() {
        return new R(REnum.FAIL.getCode(),REnum.FAIL.getDesc());
    }

    /**
     * 失败自定义返回
     * @return
     */
    public static R successFail(String code,String message) {
        return new R(code,message);
    }

}
