package com.example.airline_reservation.exceptions;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;


@Data
@Getter
@Setter
@NoArgsConstructor
public class ResourceNotFoundException extends RuntimeException {
    private int statusCode;
    private String message;

    public ResourceNotFoundException(int statusCode, String message) {
        super(message);
        this.message = message;
        this.statusCode = statusCode;
    }
}
