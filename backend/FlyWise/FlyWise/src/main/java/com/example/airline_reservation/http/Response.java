package com.example.airline_reservation.http;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class Response {

    private String id;
    private String statusMessage;

    public Response(String id, String statusMessage) {
        this.id = id;
        this.statusMessage = statusMessage;
    }

    // getters, setters, etc
}