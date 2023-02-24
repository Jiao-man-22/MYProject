package com.jiao.testproject.testproject.mq;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.PostConstruct;

//public class QueueMessageServiceImpl implements QueueMessageService {
//
//
//    private static final Logger logger = LoggerFactory.getLogger(QueueMessageServiceImpl.class);
//
//    //@Autowired
//    //private RabbitTemplate rabbitTemplate;
//
////    @PostConstruct
////    void init(){
////        rabbitTemplate.setConfirmCallback(this);
////        rabbitTemplate.setReturnCallback(this);
////    }
//
//
////    @Override
////    public void send(Object message, String exchange, String queueRoutingKey) {
////        rabbitTemplate.convertAndSend(exchange,queueRoutingKey,message);
////    }
//
//    @Override
//    public void confirm(CorrelationData correlationData, boolean b, String s) {
//        // 消息由producer发送至exchange返回结果，发送成功时 b为true，失败时为false
//        logger.info("==========confirm==============:{},{},{}",correlationData,b,s);
//    }
//
//    @Override
//    public void returnedMessage(Message message, int i, String s, String s1, String s2) {
//        // 消息由exchange传递进入queue过程中，失败时触发
//        logger.info("===========returnedMessage=============:{},{},{},{},{}",message,i,s,s1,s2);
//    }
//}
