package com.codetron.cloud.queue.bets;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

    private static final Logger LOGGER  = LoggerFactory.getLogger(BetService.class);

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
        this.rabbitTemplate.convertAndSend("IN.DRAW", theBet);
        return theBet;
    }

    public Boolean validateBet(final Bet bet) {return bet.getUserId() > 0 && bet.getUserId() < 10;}

    public void removeBet(final Bet bet) {
        this.betRepository.delete(bet);
    }

    public List<Bet> retrieveAllBetsByCustomerId(final Long customerId) {
        return this.betRepository.findAllByUserId(customerId);
    }

    public List<Bet> retrieveWinners(final String winno) {
        return this.betRepository.findAllByNumber(winno);
    }

    public void notifyWinners(String winno) {

        this.betRepository.findAllByNumber(winno)
                .stream()
                .map(bet -> new WinnerDTO(bet))
                .forEach(winner -> notifyWinner(winner));
    }

    private void notifyWinner(final WinnerDTO winner) {
        LOGGER.info("Notifying winner {}",winner);
        this.rabbitTemplate.convertAndSend("IN.WINNER", winner);
    }
}
