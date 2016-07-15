package com.codetron.cloud.queue.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import lombok.Setter;


/**
 * Created by josete on 11/07/2016.
 */
@RestController
public class WebBetsController {


    private BetCreatorService betCreatorService;


    @Autowired
    public WebBetsController(BetCreatorService betCreatorService) {
        this.betCreatorService = betCreatorService;
    }



    @RequestMapping(value ="/bet/{USER}/{NUM}", method = RequestMethod.POST)
    public ResponseEntity<?> createBets(@PathVariable("USER") final Long user, @PathVariable("NUM") final String number) {

        this.betCreatorService.createBet(user, number);
        return ResponseEntity.status(HttpStatus.CREATED).build();

    }

    @RequestMapping(value ="/winner", method = RequestMethod.GET)
    public ResponseEntity<?> getWinner() {
        if (betCreatorService.getWinner().isPresent()) {
            return ResponseEntity.ok(betCreatorService.getWinner().get());
        } else
            return ResponseEntity.notFound().build();
    }
}
