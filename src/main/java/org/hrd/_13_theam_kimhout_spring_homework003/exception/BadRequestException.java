package org.hrd._13_theam_kimhout_spring_homework003.exception;

public class BadRequestException extends RuntimeException {
    public BadRequestException(String message) {
        super(message);
    }
}
