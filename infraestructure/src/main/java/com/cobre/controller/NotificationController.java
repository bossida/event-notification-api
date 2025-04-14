package com.cobre.controller;

import com.cobre.dto.EventInputDto;
import com.cobre.exceptions.NotificationNotFoundException;
import com.cobre.service.NotificationServiceImpl;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

}
