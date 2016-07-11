package com.codetron.cloud.queue.notification;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by josetesan on 10/07/16.
 */
@Service
public class NotificationService {

    private NotificationRepository notificationRepository;

    @Autowired
    public NotificationService(NotificationRepository notificationRepository) {
        this.notificationRepository = notificationRepository;
    }


    public Notification createBet(final Notification notification) {
        return this.notificationRepository.save(notification);
    }

    public Boolean validateBet(final Notification notification) {return true;}

    public void removeBet(final Notification notification) {
        this.notificationRepository.delete(notification);
    }

    public List<Notification> retrieveAllBetsByCustomerId(final Long customerId) {
        return this.notificationRepository.findAllByCustomerId(customerId);
    }
}
