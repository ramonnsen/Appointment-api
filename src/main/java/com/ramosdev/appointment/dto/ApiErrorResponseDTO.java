package com.ramosdev.appointment.dto;

public record ApiErrorResponseDTO(
        int status,
        String message
) {
}

