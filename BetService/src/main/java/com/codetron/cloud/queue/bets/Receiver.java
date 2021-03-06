package com.codetron.cloud.queue.bets;


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

    private BetService betService;

    @Autowired
    public Receiver(BetService betService) {
        this.betService = betService;
    }


    @RabbitListener(bindings = @QueueBinding(
            value = @Queue(value = "IN.BET", durable = "false"),
            exchange = @Exchange(value = "auto.exch", ignoreDeclarationExceptions = "true")))
    public void receiveBet(final Bet bet) {
        LOGGER.info("Received a bet !, {}",bet);
        if (this.betService.validateBet(bet)) {
            this.betService.createBet(bet);
        }
    }

    @RabbitListener(bindings = @QueueBinding(
            value = @Queue(value = "OUT.DRAW", durable = "false"),
            exchange = @Exchange(value = "auto.exch", ignoreDeclarationExceptions = "true")))
    public void receiveWinner(final String winner) {
        LOGGER.info("Received the winner !!, {}",winner);
        this.betService.notifyWinners(winner);

    }

}
