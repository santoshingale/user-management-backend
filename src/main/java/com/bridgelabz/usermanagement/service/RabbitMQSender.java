package com.bridgelabz.usermanagement.service;

import com.bridgelabz.usermanagement.model.Email;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class RabbitMQSender {

    @Autowired
    private AmqpTemplate rabbitTemplate;

    @Value("${rabbitmqExchange}")
    private String exchange;

    @Value("${rabbitmqRoutingkey}")
    private String routingkey;

    public void send(Email email) {
        rabbitTemplate.convertAndSend(exchange, routingkey, email);
    }
}