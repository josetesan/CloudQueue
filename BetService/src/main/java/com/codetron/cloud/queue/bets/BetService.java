package com.codetron.cloud.queue.bets;

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
public class BetService {

    private BetRepository betRepository;

    @Autowired
    @Lazy
    private RabbitTemplate rabbitTemplate;


    @Autowired
    public BetService(BetRepository betRepository) {
        this.betRepository = betRepository;
    }

    public Bet createBet(final Bet bet) {
        final Bet theBet = this.betRepository.save(bet);
        this.rabbitTemplate.convertAndSend("OUT.BET", theBet);
        return theBet;
    }

    public Boolean validateBet(final Bet bet) {return true;}

    public void removeBet(final Bet bet) {
        this.betRepository.delete(bet);
    }

    public List<Bet> retrieveAllBetsByCustomerId(final Long customerId) {
        return this.betRepository.findAllByUserId(customerId);
    }
}
