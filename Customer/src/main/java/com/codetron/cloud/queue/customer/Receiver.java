package com.codetron.cloud.queue.customer;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by josete on 11/07/2016.
 */
@Component
public class Receiver {

    private static final Logger LOGGER = LoggerFactory.getLogger(Receiver.class);

    private CustomerService customerService;

    @Autowired
    public Receiver(final CustomerService customerService) {
        this.customerService = customerService;
    }

    @RabbitListener(bindings = @QueueBinding(
            value = @Queue(value = "IN.WINNER", durable = "false"),
            exchange = @Exchange(value = "auto.exch", ignoreDeclarationExceptions = "true")))
    public void receiveUserData(final UserDTO user) {
        LOGGER.info("Received data for an user, {}",user);
        this.customerService
                .retrieveCustomer(user.getId())
                .map(c -> this.customerService.notifyWinner(c.getEmail(), user))
                .subscribe();
        LOGGER.error("Winner not found {}", user.getId());
    }
}
