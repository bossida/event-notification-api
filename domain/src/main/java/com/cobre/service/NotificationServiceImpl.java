package com.cobre.service;

import com.cobre.api.NotificationService;
import com.cobre.dto.EventInputDto;
import com.cobre.enums.NotificationStatus;
import com.cobre.model.EventNotification;
import com.cobre.model.Notification;
import com.cobre.model.NotificationPublish;
import com.cobre.spi.EventNotificationProvider;
import com.cobre.spi.EventNotificationPublisher;
import com.cobre.spi.NotificationProvider;
import org.apache.logging.log4j.message.StringFormattedMessage;

import java.time.LocalDateTime;

public class NotificationServiceImpl implements NotificationService {

    private EventNotificationProvider eventNotificationProvider;
    private EventNotificationPublisher eventNotificationPublisher;
    private NotificationProvider notificationProvider;

    public NotificationServiceImpl(EventNotificationProvider eventNotificationProvider,
                                   EventNotificationPublisher eventNotificationPublisher,
                                   NotificationProvider notificationProvider){
        this.eventNotificationProvider = eventNotificationProvider;
        this.eventNotificationPublisher = eventNotificationPublisher;
        this.notificationProvider = notificationProvider;

    }

    private Notification createNotification(EventNotification eventNotification, EventInputDto event, boolean delivered, NotificationStatus notificationStatus) {
        var notification = new Notification(
                delivered, event.clientId(), eventNotification.eventId(), LocalDateTime.now(), 0,
                event.message(), notificationStatus);
        return notification;
    }

    private void saveError(EventInputDto event) {
    }


    public void sendNotification(EventInputDto event){
        var eNotificationOpt = eventNotificationProvider.getEventsByClientIdAndEventId(event.clientId(), event.eventId());
        if (eNotificationOpt.isEmpty()){
            System.out.println("The event was not found for client id " + event.clientId() + " - eventId " + event.eventId());
            saveError(event);
            return;
        }
        var eventNotification = eNotificationOpt.get();

        if (eventNotification.deliver()){
            var notificationPublish = new NotificationPublish( event.message(),eventNotification.webhookUrl());
            var status = eventNotificationPublisher.sendNotification(notificationPublish);
            var delivered = NotificationStatus.SUCCESS == status;
            var notification = createNotification(eventNotification, event, delivered, status);
            notificationProvider.saveNotification(notification);
        }else{
            var notification = createNotification(eventNotification, event, false, NotificationStatus.SUCCESS);
            notificationProvider.saveNotification(notification);
        }

    }



}
