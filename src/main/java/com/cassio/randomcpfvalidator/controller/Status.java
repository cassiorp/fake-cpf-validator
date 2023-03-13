package com.cassio.randomcpfvalidator.controller;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.http.HttpStatusCode;

@Data
@AllArgsConstructor
public class Status {
    private String message;
    private HttpStatusCode statusCode;
}
