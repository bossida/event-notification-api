package com.cobre.service;

import com.cobre.annotations.DomainService;
import com.cobre.api.NotificationService;
import com.cobre.dto.EventInputDto;
import com.cobre.enums.NotificationStatus;
import com.cobre.exceptions.NotificationNotFoundException;
import com.cobre.model.EventNotification;
import com.cobre.model.Notification;
import com.cobre.model.NotificationPublish;
import com.cobre.spi.EventNotificationProvider;
import com.cobre.spi.EventNotificationPublisher;
import com.cobre.spi.NotificationProvider;
import org.apache.logging.log4j.message.StringFormattedMessage;

import java.time.LocalDateTime;

@DomainService
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

    private Notification createNotification(Integer eventId, EventInputDto event, boolean delivered, NotificationStatus notificationStatus) {
        return  new Notification(
                delivered, event.clientId(),eventId, LocalDateTime.now(), 0,
                event.message(), notificationStatus);

    }

    private void saveError(EventInputDto event) {
        var notification = createNotification(event.eventId(), event, false, NotificationStatus.ERROR);
        notificationProvider.saveNotification(notification);
    }


    public void sendNotification(EventInputDto event) throws NotificationNotFoundException {
        var eNotificationOpt = eventNotificationProvider.getEventsByClientIdAndEventId(event.clientId(), event.eventId());
        if (eNotificationOpt.isEmpty()){
            String error = "The event was not found for client id " + event.clientId() + " - eventId " + event.eventId();
            System.out.println(error);
            saveError(event);
            throw new NotificationNotFoundException(error);
        }
        var eventNotification = eNotificationOpt.get();

        if (eventNotification.deliver()){
            var notificationPublish = new NotificationPublish( event.message(),eventNotification.webhookUrl());
            var status = eventNotificationPublisher.sendNotification(notificationPublish);
            var delivered = NotificationStatus.SUCCESS == status;
            var notification = createNotification(eventNotification.eventId(), event, delivered, status);
            notificationProvider.saveNotification(notification);
        }else{
            var notification = createNotification(eventNotification.eventId(), event, false, NotificationStatus.SUCCESS);
            notificationProvider.saveNotification(notification);
        }

    }



}
