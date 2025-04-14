package com.cobre.model;

import java.time.LocalDateTime;

public record EventNotification (
     boolean deliver,
     Long clientId,
     String webhookUrl,
     Integer eventId,
     LocalDateTime creationDate,
     Long id
){

}
