package com.jiao.testproject.testproject.mq;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

//@Component
public class ConsumerService {

    private static final Logger logger = LoggerFactory.getLogger(ConsumerService.class);

 //   @RabbitListener(queues = RabbitmqConfiConsumer.ITEM_QUEUE)
 //   @RabbitHandler
    public void consumeMessage(Message message){
        logger.info("收到的消息:{}",message);
    }

}
