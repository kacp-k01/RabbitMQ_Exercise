package com.example.RabbitMQDemo;

import com.example.RabbitMQDemo.consumer.User;
import com.example.RabbitMQDemo.dto.Order;
import com.example.RabbitMQDemo.dto.OrderStatus;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.times;

@ExtendWith(SpringExtension.class)
@SpringBootTest
class UserTest {

    @Autowired
    private User user;

    @MockBean
    private RabbitTemplate rabbitTemplate;
    @Test
    void testConsumeMessageFromQueue() {
        OrderStatus orderStatus = new OrderStatus(new Order("123", "Pizza", 2, 10.0), "process", "order placed successfully");
        user.consumeMessageFromQueue(orderStatus);
        verify(rabbitTemplate, times(0)).convertAndSend("exchangeName", "routingKey", orderStatus);
    }
}