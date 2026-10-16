package com.ramosdev.appointment.handler;

import com.ramosdev.appointment.dto.ApiErrorResponseDTO;
import com.ramosdev.appointment.exception.ServiceOfferingAlreadyExistsException;
import com.ramosdev.appointment.exception.ServiceOfferingNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ServiceOfferingExceptionHandler {

    @ExceptionHandler(ServiceOfferingNotFoundException.class)
    public ResponseEntity<ApiErrorResponseDTO> serviceOfferingNotFoundHandler(ServiceOfferingNotFoundException exception){
        ApiErrorResponseDTO apiErrorResponseDTO = new ApiErrorResponseDTO(
                HttpStatus.NOT_FOUND.value(),
                exception.getMessage()
        );

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(apiErrorResponseDTO);
    }

    @ExceptionHandler(ServiceOfferingAlreadyExistsException.class)
    public ResponseEntity<ApiErrorResponseDTO> serviceOfferingAlreadyExistsHandler(ServiceOfferingAlreadyExistsException exception){
        ApiErrorResponseDTO apiErrorResponseDTO = new ApiErrorResponseDTO(
                HttpStatus.CONFLICT.value(),
                exception.getMessage()
        );

        return ResponseEntity.status(HttpStatus.CONFLICT).body(apiErrorResponseDTO);
    }
}
