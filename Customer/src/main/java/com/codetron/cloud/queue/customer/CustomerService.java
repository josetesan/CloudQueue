package com.codetron.cloud.queue.customer;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by josetesan on 10/07/16.
 */
@Service
public class CustomerService {


    private CustomerRepository customerRepository;


    private RabbitTemplate rabbitTemplate;

    @Autowired
    @Lazy
    public void setRabbitTemplate(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
        this.rabbitTemplate.setMessageConverter(new Jackson2JsonMessageConverter());
    }

    @Autowired
    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }


    public Customer retrieveCustomer(final Long id) {
        return this.customerRepository.findOne(id);
    }

    public Customer saveCustomer(final Customer customer) {
        return this.customerRepository.save(customer);
    }


    public List<Customer> retrieveAll() {
        return this.customerRepository.findAll();
    }

    public void notifyWinner(final String email, final UserDTO user) {

        final NotificationDTO notificationDTO = NotificationDTO.builder()
                .email(email)
                .number(user.getNumber())
                .prize(user.getPrize())
                .build();

        this.rabbitTemplate.convertAndSend("IN.NOTIFICATION", notificationDTO);

    }
}
