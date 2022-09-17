package com.example.demo12.Listener;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;

import java.util.Map;

//@RabbitListener(queues = "topic.woman")
//@Component
public class TopicTotalReceiver {

    @RabbitHandler
    public void  process(Map testMessage) {
        System.out.println("TopicTotalReceiver消费者收到消息  : " + testMessage.toString());
    }
}
