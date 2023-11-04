package com.example.RabbitMQDemo;

import com.example.RabbitMQDemo.dto.OrderStatus;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
class OrderPublisherTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private RabbitTemplate rabbitTemplate;
    @Test
    void testBookOrder() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/order/restaurantName")
                        .content("{\"name\":\"Pizza\", \"qty\":2, \"price\":10.0}")
                        .contentType("application/json"))
                .andReturn();
        verify(rabbitTemplate).convertAndSend(any(String.class), any(String.class), any(OrderStatus.class));
    }
}