package com.cobre.spi.stubs;

import com.cobre.model.EventNotification;
import com.cobre.model.Notification;
import com.cobre.spi.NotificationProvider;

import java.util.List;

public class NotificationProviderStub implements NotificationProvider {
    private List<Notification> notificationList;

    @Override
    public void saveNotification(Notification notification) {
        notificationList.add(notification);
    }

}
