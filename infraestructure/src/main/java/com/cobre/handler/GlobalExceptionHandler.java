package com.cobre.handler;

import com.cobre.exceptions.NotificationNotFoundException;
import com.cobre.exceptions.WebHookClientNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.HashMap;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(NotificationNotFoundException.class)
    public ResponseEntity<Object> handleNotificationNotFound(NotificationNotFoundException ex) {
        var body = new HashMap<String, Object>();
        body.put("message", ex.getMessage());
        return new ResponseEntity<>(body, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(WebHookClientNotFoundException.class)
    public ResponseEntity<Object> handleWebClientNotFound(WebHookClientNotFoundException ex) {
        var body = new HashMap<String, Object>();
        body.put("message", ex.getMessage());
        return new ResponseEntity<>(body, HttpStatus.NOT_FOUND);
    }

}
