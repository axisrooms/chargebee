package com.axisrooms.model;

import lombok.Data;

@Data
public class Response {
    private String message;
    private int httpStatusCode;
}
