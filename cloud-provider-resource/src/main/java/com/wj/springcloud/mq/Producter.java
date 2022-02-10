package com.wj.springcloud.mq;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author ：actor
 * @date ：Created in 2021/8/11 15:38
 * @description：生产者
 * @modified By：
 * @version: $
 */
@Component
public class Producter {

    @Autowired
    private AmqpTemplate rabbitTemplate;


    public void send() {
        //参数1:队列名
        //参数2:发送内容
        rabbitTemplate.convertAndSend("hello","你好");
    }

    public void sendMessage(){
        // 参数1:是交换机
        // 参数2:是绑定的routingKey
        // 参数3:发送的信息
        String a = "zz";
        this.rabbitTemplate.convertAndSend("topicExchange","test_queue",a);
    }

    public void sendMessage(String exchange,String routingkey,String content){
        // 参数1:是交换机
        // 参数2:是绑定的routingKey
        // 参数3:发送的信息
        this.rabbitTemplate.convertAndSend(exchange,routingkey,content);
    }

}
