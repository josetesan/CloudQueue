/******************************************************************************
 * Copyright (C) 2005 - 2016 Ventura24 S.L.U.                                *
 * *
 * Copyright and license details are included in Ventura24 license file.     *
 ******************************************************************************/
package com.codetron.cloud.queue.notification;

import org.springframework.beans.factory.annotation.Autowired;

/**
 *****************************************************************************
 *  Copyright (C) 2005 - 2016 Ventura24 S.L.U.                                *
 *                                                                            *
 *  Copyright and license details are included in Ventura24 license file.     *
 ******************************************************************************
 * Created by jsanc on 11/07/16.
 */
public class Receiver {

    private NotificationService notificationService;

    @Autowired
    public Receiver(final NotificationService notificationService) {
        this.notificationService = notificationService;
    }

    public void sendNotification(final NotificationDTO notification) {
        this.notificationService.saveNotification(notification);
        this.notificationService.sendNotification(notification);
    }
}
