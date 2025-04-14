package com.cobre.spi.stubs;

import com.cobre.model.EventNotification;
import com.cobre.spi.EventNotificationProvider;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public class EventNotificationProviderStub implements EventNotificationProvider {

    private List<EventNotification> createEventNotifications(){
        var event1 = new EventNotification(true,
                10L, "webhook1", 1,
                LocalDateTime.now(),1L);
        return List.of(event1);
    }

    public Optional<EventNotification> getEventsByClientIdAndEventId(Long clientId, Integer eventId) {
        var events = createEventNotifications();
        return events.stream().filter(x-> x.clientId().equals(clientId) && x.eventId().equals(eventId)).findFirst();

    }

}
