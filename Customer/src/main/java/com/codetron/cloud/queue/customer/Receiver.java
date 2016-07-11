package com.codetron.cloud.queue.customer;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

    }


    public void receiveUserData(final UserDTO user) {
        LOGGER.info("Received data for an user, {}",user);
        final Customer customer = this.customerService.retrieveCustomer(user.getId());
        this.customerService.notifyWinner(customer.getEmail(), user);
    }
}
