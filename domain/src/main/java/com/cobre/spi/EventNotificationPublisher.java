package com.cobre.spi;

import com.cobre.enums.NotificationStatus;
import com.cobre.exceptions.WebHookClientNotFoundException;
import com.cobre.model.EventNotification;
import com.cobre.model.Notification;
import com.cobre.model.NotificationPublish;

public interface EventNotificationPublisher {

    public NotificationStatus sendNotification(NotificationPublish notificationPublish) throws WebHookClientNotFoundException;

}
