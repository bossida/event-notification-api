package com.cobre.spi.stubs;

import com.cobre.enums.NotificationStatus;
import com.cobre.model.EventNotification;
import com.cobre.model.NotificationPublish;
import com.cobre.spi.EventNotificationPublisher;

import java.util.List;

public class EventNotificationPublisherStub implements EventNotificationPublisher {
    private List<NotificationPublish> notificationList;

    @Override
    public NotificationStatus sendNotification(NotificationPublish eventNotification) {
        notificationList.add(eventNotification);
        return NotificationStatus.SUCCESS;
    }


}
