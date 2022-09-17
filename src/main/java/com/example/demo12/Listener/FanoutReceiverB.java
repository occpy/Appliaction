package com.example.demo12.Listener;


import org.springframework.amqp.rabbit.annotation.RabbitHandler;

import java.io.IOException;
import java.util.Map;

//@Component
//@RabbitListener(queues = "fanout.B")
public class FanoutReceiverB {
    @RabbitHandler
    public void process(Map testMessage) throws IOException {
        System.out.println("FanoutReceiverB消费者收到消息  : " +testMessage.toString());
        WebSocketServer.sendInfo("FanoutReceiverB消费者收到消息  : " +testMessage.toString(), String.valueOf(1));
    }

}
