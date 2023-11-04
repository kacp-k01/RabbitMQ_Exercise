package com.example.RabbitMQDemo.config;

import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;

@TestConfiguration
@EnableRabbit
public class RabbitMQTestConfig {

    @Bean
    @Primary
    public ConnectionFactory testConnectionFactory() {
        return new CachingConnectionFactory("test");
    }
    @Bean
    public RabbitTemplate testRabbitTemplate(ConnectionFactory testConnectionFactory) {
        RabbitTemplate rabbitTemplate = new RabbitTemplate(testConnectionFactory);
        rabbitTemplate.setMessageConverter(new Jackson2JsonMessageConverter());
        return rabbitTemplate;
    }
}
