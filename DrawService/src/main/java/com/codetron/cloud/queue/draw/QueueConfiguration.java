package com.codetron.cloud.queue.draw;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.amqp.rabbit.listener.adapter.MessageListenerAdapter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import lombok.Getter;

/**
 * Created by josete on 11/07/2016.
 */
@Configuration
public class QueueConfiguration {

    private String inQueueName = "IN.BET";
    private String outQueueName = "OUT.DRAW";

    @Bean
    Queue inQueue() {
        return new Queue(inQueueName, false);
    }

    @Bean
    Queue outQueue() { return new Queue(outQueueName,false);}

    @Bean
    TopicExchange exchange() {
        return new TopicExchange("spring-boot-exchange");
    }

    @Bean
    Binding inBinding() {
        return BindingBuilder.bind(inQueue()).to(exchange()).with(inQueueName);
    }

    @Autowired
    private DrawService drawService;

    @Bean
    public Receiver receiver() {
        return new Receiver(drawService);
    }

    @Bean
    Binding outBinding() {
        return BindingBuilder.bind(outQueue()).to(exchange()).with(outQueueName);
    }


    @Bean
    SimpleMessageListenerContainer container(final ConnectionFactory connectionFactory,final  MessageListenerAdapter listenerAdapter) {
        SimpleMessageListenerContainer container = new SimpleMessageListenerContainer();
        container.setConnectionFactory(connectionFactory);
        container.setQueueNames(inQueueName);
        container.setMessageListener(listenerAdapter);
        container.setConcurrentConsumers(5);
        return container;
    }

    @Bean
    MessageListenerAdapter listenerAdapter() {
        return new MessageListenerAdapter(receiver(), "createDraw");
    }
}
