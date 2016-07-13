/******************************************************************************
 * Copyright (C) 2005 - 2016 Ventura24 S.L.U.                                *
 * *
 * Copyright and license details are included in Ventura24 license file.     *
 ******************************************************************************/
package com.codetron.cloud.queue.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 *****************************************************************************
 *  Copyright (C) 2005 - 2016 Ventura24 S.L.U.                                *
 *                                                                            *
 *  Copyright and license details are included in Ventura24 license file.     *
 ******************************************************************************
 * Created by jsanc on 11/07/16.
 */
@Component
public class Receiver {

    private static final Logger LOGGER = LoggerFactory.getLogger(Receiver.class);

    private WebBetsController webBetsController;

    @Autowired
    public void setWebBetsController(final WebBetsController webBetsController) {
        this.webBetsController = webBetsController;
    }

    @RabbitListener(bindings = @QueueBinding(
            value = @Queue(value = "OUT.WINNER", durable = "false"),
            exchange = @Exchange(value = "auto.exch", ignoreDeclarationExceptions = "true")))
    public void receiveDrawWinner(final WinnerDTO winnerDTO) {

        LOGGER.info("Received winner !!, {}" , winnerDTO);
        this.webBetsController.setWinnerDTO(winnerDTO);

    }
}
