package com.wj.springcloud.result;

import com.alibaba.fastjson.JSON;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;
import java.util.Objects;

/**
 * @author ：actor
 * @date ：Created in 2021/7/29 17:15
 * @description：公共返回处理类
 * @modified By：
 * @version: $
 */
@RestControllerAdvice
public class CommonResponseAdvice implements ResponseBodyAdvice {

    /**
     * 进行一些逻辑判断是否需要进行后续的beforeBodyWrite的方法
     * @param methodParameter
     * @param aClass
     * @return
     */
    @Override
    public boolean supports(MethodParameter methodParameter, Class aClass) {
        return filter(methodParameter);
    }

    /**
     * 根据返回类型来判断是否需要进行后续操作
     * @param methodParameter
     * @return
     */
    private boolean filter(MethodParameter methodParameter) {
        return true;
    }


    @Override
    public Object beforeBodyWrite(Object value, MethodParameter methodParameter, MediaType mediaType, Class aClass, ServerHttpRequest serverHttpRequest, ServerHttpResponse serverHttpResponse) {
       R r;
       //swagger 请求就直接返回,不做处理
        String swagger = JSON.toJSONString(value);
        if(swagger.indexOf("swagger") !=-1){
            return value;
        }
        //获取返回值类型
       String returnClassType  = methodParameter.getParameterType().getName();
       if("void".equals(returnClassType)){
           r =  R.successOk();
       }else if ("com.wj.springcloud.result.R".equals(returnClassType)){
           r =   (R)value;
       }else if("String".equals(returnClassType)){
           //json格式会出现问题
           r = R.successOk(value);
           return JSON.toJSONString(r);
       }else {
           if (Objects.isNull(value)){
               r = R.successOk();
           }else {
               r = R.successOk(value);
           }
       }
        //一定要转换为String，否则会报错
        return r;
    }
}
