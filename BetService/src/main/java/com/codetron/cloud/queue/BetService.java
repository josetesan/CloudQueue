package com.codetron.cloud.queue;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by josetesan on 10/07/16.
 */
@Service
public class BetService {

    private BetRepository betRepository;

    @Autowired
    public BetService(BetRepository betRepository) {
        this.betRepository = betRepository;
    }


    public Bet createBet(final Bet bet) {
        return this.betRepository.save(bet);
    }

    public Boolean validateBet(final Bet bet) {return true;}

    public void removeBet(final Bet bet) {
        this.betRepository.delete(bet);
    }

    public List<Bet> retrieveAllBetsByCustomerId(final Long customerId) {
        return this.betRepository.findAllByCustomerId(customerId);
    }
}
