package com.ramosdev.appointment.controller;

import com.ramosdev.appointment.dto.PageResponse;
import com.ramosdev.appointment.dto.ServiceOfferingCreateDTO;
import com.ramosdev.appointment.dto.ServiceOfferingRequestDTO;
import com.ramosdev.appointment.dto.ServiceOfferingResponseDTO;
import com.ramosdev.appointment.service.ServiceOfferingService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/service-offerings")
public class ServiceOfferingController {
    private final ServiceOfferingService serviceOfferingService;

    public ServiceOfferingController(ServiceOfferingService serviceOfferingService) {
        this.serviceOfferingService = serviceOfferingService;
    }

    @PostMapping
    public ResponseEntity<ServiceOfferingResponseDTO> create(@RequestBody @Valid ServiceOfferingCreateDTO createDTO) {
        return ResponseEntity.status(HttpStatus.CREATED).body(serviceOfferingService.createEntity(createDTO));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ServiceOfferingResponseDTO> findById(@PathVariable UUID id) {
        return ResponseEntity.ok().body(serviceOfferingService.findById(id));
    }

    @GetMapping
    public ResponseEntity<PageResponse<ServiceOfferingResponseDTO>> findAll(@PageableDefault Pageable pageable) {
        return ResponseEntity.ok().body(serviceOfferingService.findAll(pageable));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ServiceOfferingResponseDTO> update(@PathVariable UUID id, @RequestBody @Valid ServiceOfferingRequestDTO requestDTO) {
        return ResponseEntity.ok().body(serviceOfferingService.updateEntity(id, requestDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        serviceOfferingService.delete(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
