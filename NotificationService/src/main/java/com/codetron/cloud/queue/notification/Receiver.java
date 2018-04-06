/******************************************************************************
 * Copyright (C) 2005 - 2016 ACME S.L.U.                                *
 * *
 * Copyright and license details are included in ACME license file.     *
 ******************************************************************************/
package com.codetron.cloud.queue.notification;

import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

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

    private NotificationService notificationService;

    @Autowired
    public Receiver(final NotificationService notificationService) {
        this.notificationService = notificationService;
    }

    @RabbitListener(bindings = @QueueBinding(
            value = @Queue(value = "IN.NOTIFICATION", durable = "false"),
            exchange = @Exchange(value = "auto.exch", ignoreDeclarationExceptions = "true")))
    public void sendNotification(final NotificationDTO notification) {
        this.notificationService.saveNotification(notification);
        this.notificationService.sendNotification(notification);
    }
}
