package com.cobre.controller;

import com.cobre.dto.EventInputDto;
import com.cobre.exceptions.NotificationNotFoundException;
import com.cobre.model.Notification;
import com.cobre.service.NotificationServiceImpl;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/notifications")
public class NotificationController {

    private NotificationServiceImpl notificationService;



    public NotificationController(NotificationServiceImpl notificationService) {
        this.notificationService = notificationService;
    }

    @PostMapping
    public void sendNotification(@RequestBody EventInputDto event) throws NotificationNotFoundException {
        notificationService.sendNotification(event);
    }

    @GetMapping
    public List<Notification> getAllNotification() throws NotificationNotFoundException {
        return notificationService.getNotifications();
    }

}
