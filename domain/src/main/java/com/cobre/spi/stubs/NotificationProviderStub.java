package com.cobre.spi.stubs;

import com.cobre.model.EventNotification;
import com.cobre.model.Notification;
import com.cobre.spi.NotificationProvider;

import java.util.ArrayList;
import java.util.List;

public class NotificationProviderStub implements NotificationProvider {
    private final List<Notification> notificationList = new ArrayList<Notification>();

    @Override
    public void saveNotification(Notification notification) {
        notificationList.add(notification);
    }

    @Override
    public List<Notification> getNotificationList(){
        return notificationList;
    }
}
