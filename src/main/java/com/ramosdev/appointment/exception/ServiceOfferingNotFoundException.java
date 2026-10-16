package com.ramosdev.appointment.exception;

public class ServiceOfferingNotFoundException extends RuntimeException {
    public ServiceOfferingNotFoundException(String message) {
        super(message);
    }
}
