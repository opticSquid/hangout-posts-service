package com.hangout.core.post_service.exceptions;

public class MotherOfAllExceptions extends RuntimeException {
    public MotherOfAllExceptions(String clientMessage) {
        super(clientMessage);
    }
}
