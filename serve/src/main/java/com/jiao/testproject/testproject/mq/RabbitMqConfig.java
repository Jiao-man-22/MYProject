package com.jiao.testproject.testproject.mq;

import com.rabbitmq.client.AMQP;
import org.springframework.amqp.core.*;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

//@Configuration
public class RabbitMqConfig {

    //交换机名称
    public static final String ITEM_TOPIC_EXCHANGE = "item_topic_exchange";

    //队列名称
    public static final String ITEM_QUEUE="item_queue";

    //声明交换机
    //@Bean("itemTopicExchange")
    public AMQP.Exchange topicExchange(){
        return ExchangeBuilder.topicExchange(ITEM_TOPIC_EXCHANGE).durable(true).build();
    }

    //声明队列
    //@Bean("itemQueue")
    public Queue itemQueue(){
        return QueueBuilder.durable(ITEM_QUEUE).build();
    }

    //绑定队列和交换机
    //@Bean
    public Binding itemQueueExchage(@Qualifier("itemQueue")Queue itemQueue,
                                    @Qualifier("itemTopicExchange") AMQP.Exchange itemTopicExchange){
        return BindingBuilder.bind(itemQueue).to((Exchange) itemTopicExchange).with("item.#").noargs();
    }


}
