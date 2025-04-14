package com.cobre.api;

import com.cobre.dto.EventInputDto;
import com.cobre.exceptions.NotificationNotFoundException;
import com.cobre.exceptions.WebHookClientNotFoundException;
import com.cobre.model.Notification;

import java.util.List;

public interface NotificationService {
    public void sendNotification(EventInputDto event) throws NotificationNotFoundException, WebHookClientNotFoundException;
    public List<Notification> getNotifications();
}
