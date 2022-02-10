package com.wj.springcloud.config;

import com.wj.springcloud.constant.RabbitMqDef;
import org.springframework.amqp.core.*;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author ：actor
 * @date ：Created in 2021/8/11 16:42
 * @description：rabbitmq 配置中心
 * @modified By：
 * @version: $
 */
@Configuration
public class RabbitMqConfig {

    /**
     * 创建队列
     */
    @Bean(name="message")
    public Queue queueMessage(){
        //test_queue 是rounting-key,匹配规则
        return  new Queue("test_queue");
    }


    /**
     * 创建交换机
     * @return
     */
    @Bean
    public TopicExchange exchange(){
        return new TopicExchange("topicExchange");
    }

    /**
     * 绑定交换机
     * @param queueMessage
     * @param exchange
     * @return
     */
    @Bean
    public Binding bindingExchangeMessage(@Qualifier("message") Queue queueMessage, TopicExchange exchange){
        return BindingBuilder.bind(queueMessage).to(exchange).with("test_queue");
    }

    /**
     * 延迟队列
     */
    @Bean(name = "delay_queue")
    public Queue delayQueuePerMessageTTL(){
        return QueueBuilder.durable(RabbitMqDef.RabbitQueue.DELAY_QUEUE_PER_MESSAGE_TTL_NAME)
                //死信交换机
                .withArgument("x-dead-letter-exchange",RabbitMqDef.ExchangeParam.DEAD_LETTER_EXCHANGE_NAME)
                //路由key
                .withArgument("x-dead-letter-routing-key",RabbitMqDef.Routing.DELAY_PROCESS_ROUTING_NAME)
                //过期时间
                .withArgument("x-message-ttl", 10000)
                .build();
    }

    /**
     *业务队列
     */
    @Bean("process_queue")
    public Queue processQueue(){
        return  QueueBuilder.durable(RabbitMqDef.RabbitQueue.PROCESS_QUEUE_NAME).build();
    }

    /**
     *业务交换机
     */
    @Bean(name = "process_exchange")
    public DirectExchange processExchange(){
        return new DirectExchange(RabbitMqDef.ExchangeParam.PROCESS_EXCHANGE_NAME);
    }

    @Bean
    public Binding processBinding(@Qualifier("process_queue") Queue queue,@Qualifier("process_exchange") DirectExchange directExchange){
        return BindingBuilder.bind(queue).to(directExchange).with(RabbitMqDef.Routing.PROCESS_ROUTING_NAME);
    }


    /**
     * 死信交换机
     */
    @Bean
    public DirectExchange  delayExchange(){
        return new DirectExchange(RabbitMqDef.ExchangeParam.DEAD_LETTER_EXCHANGE_NAME);
    }

    /**
     * 绑定死信队列
     */
    @Bean
    public Binding dlxBinding(@Qualifier("process_queue") Queue queue,DirectExchange delayExchange){
        return BindingBuilder.bind(queue).to(delayExchange).with(RabbitMqDef.Routing.DELAY_PROCESS_ROUTING_NAME);
    }


}
