package com.hangout.core.post.exceptions;

public class MotherOfAllExceptions extends RuntimeException {
    public MotherOfAllExceptions(String clientMessage) {
        super(clientMessage);
    }
}
