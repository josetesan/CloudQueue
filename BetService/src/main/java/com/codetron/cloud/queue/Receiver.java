package com.codetron.cloud.queue;


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

    private BetService betService;

    @Autowired
    public Receiver(BetService betService) {
        this.betService = betService;
    }

    public void receiveBet(final Bet bet) {
        LOGGER.info("Received a bet !, {}",bet);
        if (this.betService.validateBet(bet)) {
            this.betService.createBet(bet);
        }
    }
}
