package com.codetron.cloud.queue.notification;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

/**
 * Created by josetesan on 10/07/16.
 */
@Service
public class NotificationService {

    private NotificationRepository notificationRepository;

    private MailSender mailSender;

    @Autowired
    @Lazy
    public void setMailSender(final MailSender mailSender) {
        this.mailSender = mailSender;
    }

    @Autowired
    @Lazy
    public void setNotificationRepository(final NotificationRepository notificationRepository) {
        this.notificationRepository = notificationRepository;
    }

    public void saveNotification(final NotificationDTO theNotification) {

        final Notification notification = Notification.builder()
                .email(theNotification.getEmail())
                .number(theNotification.getNumber())
                .prize(theNotification.getPrize())
                .createDate(LocalDate.now())
                .build();

        this.notificationRepository.save(notification);

    }

    public void sendNotification(final NotificationDTO notification) {

        final SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setFrom("winners@sorteo.com");
        final String text = String.format("Congratulations %s, your %s bet has won %.2f Euros",
                notification.getEmail(),
                notification.getNumber(),
                notification.getPrize());
        simpleMailMessage.setText(text);
        simpleMailMessage.setTo(notification.getEmail());
        this.mailSender.send(simpleMailMessage);
    }
}
