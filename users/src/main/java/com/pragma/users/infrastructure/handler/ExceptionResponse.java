package com.pragma.users.infrastructure.handler;

public enum ExceptionResponse {
    NO_DATA_FOUND("no data found for the request petition");
    private final String message;

    ExceptionResponse(String message) {
        this.message = message;
    }
    public String getMessage() {
        return message;
    }

}
