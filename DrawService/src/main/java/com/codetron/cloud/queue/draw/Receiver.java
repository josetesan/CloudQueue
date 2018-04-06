/******************************************************************************
 * Copyright (C) 2005 - 2016 ACME S.L.U.                                *
 * *
 * Copyright and license details are included in ACME license file.     *
 ******************************************************************************/
package com.codetron.cloud.queue.draw;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.concurrent.atomic.AtomicInteger;

/**
 *****************************************************************************
 *  Copyright (C) 2005 - 2016 ACME S.L.U.                                *
 *                                                                            *
 *  Copyright and license details are included in ACME license file.     *
 ******************************************************************************
 * Created by jsanc on 11/07/16.
 */
@Component
public class Receiver {

    private static final Logger LOGGER = LoggerFactory.getLogger(Receiver.class);

    private static final AtomicInteger counter = new AtomicInteger(0);

    private DrawService drawService;

    private final static Integer MAX_BETS = 9;

    @Autowired
    public Receiver(final DrawService drawService) {
        this.drawService = drawService;
    }

    @RabbitListener(bindings = @QueueBinding(
            value = @Queue(value = "IN.DRAW", durable = "false"),
            exchange = @Exchange(value = "auto.exch", ignoreDeclarationExceptions = "true")))
    public void createDraw(final BetDTO bet) {
        LOGGER.info("Draw received a bet {}", bet);
        if (counter.compareAndSet(MAX_BETS,0)) {
            // let's play draw !
            drawService.playDraw();
        } else {
            LOGGER.info("Storing number, bets received {}",  counter.incrementAndGet());
            this.drawService.storeNumber(bet.getNumber());
        }

    }
}
