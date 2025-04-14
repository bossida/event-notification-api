package com.cobre.configuration;

import com.cobre.EventNotificationPublisherImpl;
import com.cobre.api.NotificationService;
import com.cobre.service.NotificationServiceImpl;
import com.cobre.spi.EventNotificationProvider;
import com.cobre.spi.EventNotificationPublisher;
import com.cobre.spi.NotificationProvider;
import com.cobre.spi.stubs.EventNotificationProviderStub;
import com.cobre.spi.stubs.EventNotificationPublisherStub;
import com.cobre.spi.stubs.NotificationProviderStub;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;


@Configuration
public class DomainConfiguration {

    @Bean
    public NotificationServiceImpl createNotificationService(){
        EventNotificationProvider eventNotificationProvider = new EventNotificationProviderStub();
        EventNotificationPublisher eventNotificationPublisher = new EventNotificationPublisherImpl();
        NotificationProvider notificationProvider = new NotificationProviderStub();
        var notificationService = new NotificationServiceImpl(eventNotificationProvider,eventNotificationPublisher, notificationProvider);
        return notificationService;
    }
}
