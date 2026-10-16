package com.ramosdev.appointment.exception;

public class ServiceOfferingAlreadyExistsException extends RuntimeException {
    public ServiceOfferingAlreadyExistsException(String message) {
        super(message);
    }
}
