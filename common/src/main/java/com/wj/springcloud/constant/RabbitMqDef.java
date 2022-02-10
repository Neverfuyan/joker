package com.wj.springcloud.constant;

/**
 * @author ：actor
 * @date ：Created in 2021/8/11 16:06
 * @description：rabbitmq需要参数
 * @modified By：
 * @version: $
 */
public interface RabbitMqDef {

    /**
     * 交换机
     */
    class  ExchangeParam{
        /**
         * 测试交换机
         */
         public  static final String TEST_EXCHANGE_NAME = "test_exchange";

        /**
         * 死信交换机
         */
        public static final String DEAD_LETTER_EXCHANGE_NAME = "x-dead-letter-exchange";

        /**
         * 正常业务交换机
         */
        public static final String PROCESS_EXCHANGE_NAME = "process_exchange";
    }

    /**
     * 队列
     */
    class RabbitQueue{
        /**
         * 测试队列
         */
        public  static final String TEST_QUEUE = "test_queue";

        /**
         * 延迟队列
         */
        public static final String DELAY_QUEUE_PER_MESSAGE_TTL_NAME = "delay_queue_per_message_ttl";

        /**
         * 业务队列
         */
        public static final String PROCESS_QUEUE_NAME = "process_queue";

    }


    /**
     * 队列
     */
    class Routing{
        /**
         * 测试路由
         */
        public  static final String TEST_ROUTING = "test_routing";

        /**
         * 延迟的routingkey
         */
        public static final String DELAY_PROCESS_ROUTING_NAME = "delay_process_routing";

        /**
         * 业务routingkey
         */
        public static final String PROCESS_ROUTING_NAME = "process_routing";
    }
}
