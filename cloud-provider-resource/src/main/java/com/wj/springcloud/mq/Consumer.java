package com.wj.springcloud.mq;

import com.rabbitmq.client.Channel;
import org.springframework.amqp.core.ExchangeTypes;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.*;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Collections;
import java.util.List;

/**
 * @author ：actor
 * @date ：Created in 2021/8/11 15:38
 * @description：消费者
 * @modified By：
 * @version: $
 */
@Component
public class Consumer {


    @RabbitListener(queues = "hello")
    public void process(String hello){
        System.out.println("Receiver:" + hello);
    }

    @RabbitListener(queues ="test_queue" )
    public void receiveMessage1(Message message, Channel channel) throws IOException {

        try {

            int i = 1/0;

        } catch (Exception e) {

            e.printStackTrace();

        } finally {
            //手动确认消息
            channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
        }


      //  String routingKey = StringUtils.trim(message.getMessageProperties().getReceivedRoutingKey());
      //  System.out.println(routingKey);
    }

    @RabbitListener(bindings = @QueueBinding(value = @Queue(value = "process_queue"),
            exchange = @Exchange(value = "process_exchange",type = ExchangeTypes.DIRECT),
            key = "process_routing"))
    public void receiveMessage2(String str) {
        System.out.println("接收消息:" + str);
    }


}
