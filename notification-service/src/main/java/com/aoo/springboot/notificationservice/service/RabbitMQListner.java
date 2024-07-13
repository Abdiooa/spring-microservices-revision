package com.aoo.springboot.notificationservice.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageListener;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class RabbitMQListner implements MessageListener {

    @Override
    public void onMessage(Message message) {
        log.info("Consumed Message: {}", new String(message.getBody()));
    }
}
