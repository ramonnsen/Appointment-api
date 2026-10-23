package com.ramosdev.appointment.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record UserRequestLoginDTO(
        @NotBlank
        @Size(min = 6)
        String username,

        @NotBlank
        @Size(min = 8)
        String password
) {
}
