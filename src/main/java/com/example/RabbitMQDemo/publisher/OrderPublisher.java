package com.example.RabbitMQDemo.publisher;

import com.example.RabbitMQDemo.config.MessageConfig;
import com.example.RabbitMQDemo.dto.Order;
import com.example.RabbitMQDemo.dto.OrderStatus;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/order")
public class OrderPublisher {

    @Autowired
    private RabbitTemplate template;

    @PostMapping("/{restaurantName}")
    public String bookOrder(@RequestBody Order order, String restaurantName){
        order.setOrderId(UUID.randomUUID().toString());
        OrderStatus orderStatus = new OrderStatus(order, "process","order placed successfully");
        template.convertAndSend(MessageConfig.EXCHANGE,MessageConfig.ROUTINGKEY,orderStatus);
        return "Success";
    }
}
