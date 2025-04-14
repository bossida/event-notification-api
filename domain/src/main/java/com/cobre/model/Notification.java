package com.cobre.model;

import com.cobre.enums.NotificationStatus;

import java.time.LocalDate;
import java.time.LocalDateTime;

public record Notification (
     boolean delivered,
     Long clientId,
     Integer eventId,
     LocalDateTime creationDate,
     int retryNumber,
     String message,
     NotificationStatus status
){}
