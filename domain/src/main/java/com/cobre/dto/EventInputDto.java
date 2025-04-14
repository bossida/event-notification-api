package com.cobre.dto;


public record EventInputDto (
    Long clientId,
    Integer eventId,
    String message
){
}
