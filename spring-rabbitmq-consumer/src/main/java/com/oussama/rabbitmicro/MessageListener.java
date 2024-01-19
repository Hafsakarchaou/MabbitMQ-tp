package com.oussama.rabbitmicro;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class MessageListener {

    @RabbitListener(queues = MQConfig.QUEUE1)
    public void listener(CustomMessage message) {
        System.out.println(message);
    }

}
