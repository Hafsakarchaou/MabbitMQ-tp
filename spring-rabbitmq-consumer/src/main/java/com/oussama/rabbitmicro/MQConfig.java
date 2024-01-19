package com.oussama.rabbitmicro;

import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MQConfig {

    public static final String QUEUE1 = "2ite_micro_message_queue1";
    public static final String EXCHANGE = "2ite_micro_message_exchange";
    public static final String ROUTING_KEY1 = "message_routingKey1";

    @Bean
    public Queue queue1() {
        return  new Queue(QUEUE1);
    }
    
  
    @Bean
    public TopicExchange exchange() {
        return new TopicExchange(EXCHANGE);
    }

    @Bean
    public Binding binding1(Queue queue1, TopicExchange exchange) {
        return BindingBuilder
                .bind(queue1)
                .to(exchange)
                .with(ROUTING_KEY1);
    }
    
  

    @Bean
    public MessageConverter messageConverter() {
        return  new Jackson2JsonMessageConverter();
    }

    @Bean
    public AmqpTemplate template(ConnectionFactory connectionFactory) {
        RabbitTemplate template = new RabbitTemplate(connectionFactory);
        template.setMessageConverter(messageConverter());
        return  template;
    }

}
