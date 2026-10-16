package com.ramosdev.appointment.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;

public record ServiceOfferingRequestDTO(
        String name,

        @Positive
        BigDecimal value
) {
}
