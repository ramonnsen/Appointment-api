package com.ramosdev.appointment.dto;

import lombok.Builder;
import org.springframework.data.domain.Sort;

import java.util.List;
@Builder
public record PageResponse<T>(
    List<T> content,
    long totalElements,
    int totalPages,
    int number,
    int numberOfElements,
    int size
) {
}
