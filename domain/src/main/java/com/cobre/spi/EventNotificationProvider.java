package com.cobre.spi;

import com.cobre.model.EventNotification;

import java.util.List;
import java.util.Optional;

public interface EventNotificationProvider {
    Optional<EventNotification> getEventsByClientIdAndEventId(Long clientId, Integer eventId);
}
