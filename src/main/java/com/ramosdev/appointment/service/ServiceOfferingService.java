package com.ramosdev.appointment.service;

import com.ramosdev.appointment.dto.PageResponse;
import com.ramosdev.appointment.dto.ServiceOfferingCreateDTO;
import com.ramosdev.appointment.dto.ServiceOfferingRequestDTO;
import com.ramosdev.appointment.dto.ServiceOfferingResponseDTO;
import com.ramosdev.appointment.exception.ServiceOfferingAlreadyExistsException;
import com.ramosdev.appointment.exception.ServiceOfferingNotFoundException;
import com.ramosdev.appointment.mapper.ServiceOfferingMapper;
import com.ramosdev.appointment.model.ServiceOffering;
import com.ramosdev.appointment.repository.ServiceOfferingRepository;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class ServiceOfferingService {

    private final ServiceOfferingRepository serviceOfferingRepository;
    private final ServiceOfferingMapper serviceOfferingMapper;

    public ServiceOfferingService(ServiceOfferingRepository serviceOfferingRepository, ServiceOfferingMapper serviceOfferingMapper) {
        this.serviceOfferingRepository = serviceOfferingRepository;
        this.serviceOfferingMapper = serviceOfferingMapper;
    }

    public ServiceOfferingResponseDTO createEntity(ServiceOfferingCreateDTO createDTO) {
        if (serviceOfferingRepository.existsByName(createDTO.name())) {
            throw new ServiceOfferingAlreadyExistsException("Invalid Service Offering");
        }

        ServiceOffering builtServiceOffering = ServiceOffering.builder()
                .name(createDTO.name())
                .value(createDTO.value())
                .build();
        System.out.println(builtServiceOffering.getCreatedAt());
        ServiceOffering savedEntity = serviceOfferingRepository.save(builtServiceOffering);
        System.out.println("entidade: " + savedEntity.getCreatedAt());
        return serviceOfferingMapper.toDTO(savedEntity);
    }

    public ServiceOfferingResponseDTO findById(UUID id) {
        ServiceOffering serviceOffering = serviceOfferingRepository.findById(id)
                .orElseThrow(() -> new ServiceOfferingNotFoundException("Invalid Service Offering"));

        return serviceOfferingMapper.toDTO(serviceOffering);
    }

    public PageResponse<ServiceOfferingResponseDTO> findAll(Pageable pageable) {
        Page<ServiceOffering> allEntity = serviceOfferingRepository.findAll(pageable);
        Page<ServiceOfferingResponseDTO> pageDTO = allEntity.map(serviceOfferingMapper::toDTO);

        return new PageResponse<>(
                pageDTO.getContent(),
                pageDTO.getTotalElements(),
                pageDTO.getTotalPages(),
                pageDTO.getNumber(),
                pageDTO.getNumberOfElements(),
                pageDTO.getSize()
        );
    }

    @Transactional
    public ServiceOfferingResponseDTO updateEntity(UUID id, ServiceOfferingRequestDTO requestDTO){
        ServiceOffering serviceOffering = serviceOfferingRepository.findById(id).orElseThrow(
                () -> new ServiceOfferingNotFoundException("Invalid Service Offering"));

        if(requestDTO.name() != null) serviceOffering.setName(requestDTO.name());
        if(requestDTO.value() != null) serviceOffering.setValue(requestDTO.value());

        ServiceOffering savedEntity = serviceOfferingRepository.save(serviceOffering);

        return serviceOfferingMapper.toDTO(savedEntity);
    }

    public void delete(UUID id){
        serviceOfferingRepository.deleteById(id);
    }
}
