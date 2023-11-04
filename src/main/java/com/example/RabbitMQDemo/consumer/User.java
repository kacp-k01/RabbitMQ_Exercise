package com.example.RabbitMQDemo.consumer;

import com.example.RabbitMQDemo.config.MessageConfig;
import com.example.RabbitMQDemo.dto.OrderStatus;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class User {
    @RabbitListener(queues = MessageConfig.QUEUE)
    public void consumeMessageFromQueue(OrderStatus orderStatus) {
        System.err.println("Message recieved from queue : " + orderStatus);
    }
}
