package com.codetron.cloud.queue.web;

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

/**
 * Created by josete on 11/07/2016.
 */
@Configuration
public class QueueConfiguration {

    private String outQueueName = "IN.BET";
    private String inQueueName = "OUT.DRAW";

    @Bean
    TopicExchange exchange() {
        return new TopicExchange("spring-boot-exchange");
    }

    @Bean
    Queue inBetsQueue() {
        return new Queue(inQueueName, false);
    }

    @Bean
    Binding inBinding() {
        return BindingBuilder.bind(inBetsQueue()).to(exchange()).with(inQueueName);
    }

    @Bean
    Queue outBetsQueue() {
        return new Queue(outQueueName, false);
    }

    @Bean
    Binding outBinding() {
        return BindingBuilder.bind(outBetsQueue()).to(exchange()).with(outQueueName);
    }


    @Autowired
    private WinnersService winnersService;

    @Bean
    public Receiver receiver() {
        return new Receiver();
    }


    @Bean
    SimpleMessageListenerContainer container(final ConnectionFactory connectionFactory, final MessageListenerAdapter listenerAdapter) {
        SimpleMessageListenerContainer container = new SimpleMessageListenerContainer();
        container.setConnectionFactory(connectionFactory);
        container.setQueueNames(inQueueName);
        container.setMessageListener(listenerAdapter);
        container.setConcurrentConsumers(5);
        return container;
    }

    @Bean
    MessageListenerAdapter listenerAdapter() {
        return new MessageListenerAdapter(receiver(), "receiveDrawWinner");
    }
}
