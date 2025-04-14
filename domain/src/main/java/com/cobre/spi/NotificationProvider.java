package com.cobre.spi;

import com.cobre.model.Notification;

import java.util.List;

public interface NotificationProvider {

    public void saveNotification(Notification notification);

    public List<Notification> getNotificationList();
}
