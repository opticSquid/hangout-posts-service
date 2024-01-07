package com.hangout.core.hangoutpostsservice.exceptions;

public class MotherException extends RuntimeException {
    public MotherException(String clientMessage) {
        super(clientMessage);
    }
}
