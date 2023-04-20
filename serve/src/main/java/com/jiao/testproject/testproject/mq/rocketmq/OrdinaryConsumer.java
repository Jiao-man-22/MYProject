package com.jiao.testproject.testproject.mq.rocketmq;

import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyContext;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import org.apache.rocketmq.client.consumer.listener.MessageListenerConcurrently;
import org.apache.rocketmq.common.message.MessageExt;

import java.util.List;

public class OrdinaryConsumer {
    public static void main(String[] args)  throws Exception {
        //1.创建消费者Consumer，制定消费者组名
        DefaultMQPushConsumer consumer = new DefaultMQPushConsumer("producer-group");
        //2.指定Nameserver地址
        consumer.setNamesrvAddr("192.168.101.84:9876");
        //3.订阅主题Topic和Tag
        consumer.subscribe("first-topic", "*");
        //consumer.subscribe("base", "Tag1");

        //消费所有"*",消费Tag1和Tag2  Tag1 || Tag2
        //consumer.subscribe("base", "*");

        //设定消费模式：负载均衡|广播模式  默认为负载均衡
        //负载均衡10条消息，每个消费者共计消费10条
        //广播模式10条消息，每个消费者都消费10条
        //consumer.setMessageModel(MessageModel.BROADCASTING);
        System.out.println("准备 执行消费消息方法 ");
        //4.设置回调函数，处理消息
        consumer.registerMessageListener(new MessageListenerConcurrently() {

            //接受消息内容
            @Override
            public ConsumeConcurrentlyStatus consumeMessage(List<MessageExt> msgs, ConsumeConcurrentlyContext context) {
                System.out.println("ready to print msg");
                for (MessageExt msg : msgs) {
                    //System.out.println("consumeThread=" + Thread.currentThread().getName() + "," + new String(msg.getBody()));
                    System.out.println("得到的消息 ： "+new String(msg.getBody()));
                }
                return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
            }
        });
        //5.启动消费者consumer
        consumer.start();
    }

}