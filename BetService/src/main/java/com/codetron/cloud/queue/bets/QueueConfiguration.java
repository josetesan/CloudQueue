package com.codetron.cloud.queue.bets;

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

    private String inQueueName = "IN.BET";
    private String outQueueName = "OUT.BET";

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
    private BetService betService;

    @Bean
    public Receiver receiver() {
        return new Receiver(betService);
    }

    @Bean
    Binding outBinding() {
        return BindingBuilder.bind(outQueue()).to(exchange()).with(outQueueName);
    }


    @Bean
    SimpleMessageListenerContainer container(final ConnectionFactory connectionFactory) {
        SimpleMessageListenerContainer container = new SimpleMessageListenerContainer();
        container.setConnectionFactory(connectionFactory);
        container.addQueues(inQueue());
        container.setMessageListener(listenerAdapter());
        container.setConcurrentConsumers(2);
        return container;
    }

    @Bean
    MessageListenerAdapter listenerAdapter() {
        return new MessageListenerAdapter(receiver(), "receiveBet");
    }
}
