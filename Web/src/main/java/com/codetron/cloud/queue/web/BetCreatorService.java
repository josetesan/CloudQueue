package com.codetron.cloud.queue.web;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

/**
 * Created by josete on 11/07/2016.
 */
@Service
public class BetCreatorService {


    private RabbitTemplate rabbitTemplate;

    @Autowired
    @Lazy
    public BetCreatorService(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    /**
     *
     * @param user
     * @param number
     */
    public void createBet(final Long user, final String number) {

        final BetDTO betDTO = new BetDTO(user,number);
        this.rabbitTemplate.convertAndSend("IN.BET",betDTO);

    }
}
