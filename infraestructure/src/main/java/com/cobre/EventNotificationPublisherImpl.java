package com.cobre;

import com.cobre.enums.NotificationStatus;
import com.cobre.exceptions.WebHookClientNotFoundException;
import com.cobre.model.NotificationPublish;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Component
public class EventNotificationPublisherImpl implements com.cobre.spi.EventNotificationPublisher {

    private WebClient webClient(String baseUrl) {
        return WebClient.builder()
                .baseUrl(baseUrl)
                .build();
    }

    @Override
    public NotificationStatus sendNotification(NotificationPublish notificationPublish) throws WebHookClientNotFoundException{
        var webClient = this.webClient(notificationPublish.webHook());
        webClient
                .put()
                .retrieve()
                .onStatus(status -> status.is4xxClientError(), clientResponse ->
                        clientResponse.bodyToMono(String.class)
                                .flatMap(errorBody -> {
                                    System.out.println("Client error: " + errorBody);
                                    return Mono.error(new WebHookClientNotFoundException("The webhook url was not found"));
                                }))
                .onStatus(status -> status.is5xxServerError(), clientResponse ->
                        clientResponse.bodyToMono(String.class)
                                .flatMap(errorBody -> {
                                    System.out.println("Server error: " + errorBody);
                                    return Mono.error(new WebHookClientNotFoundException("Error when calling the webhook" ));
                                }))
                .bodyToMono(String.class)
                .block();
        return NotificationStatus.SUCCESS;
    }
}
