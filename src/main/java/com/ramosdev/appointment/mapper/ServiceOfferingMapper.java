package com.ramosdev.appointment.mapper;

import com.ramosdev.appointment.dto.ServiceOfferingResponseDTO;
import com.ramosdev.appointment.model.ServiceOffering;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface ServiceOfferingMapper {


    ServiceOfferingResponseDTO toDTO(ServiceOffering serviceOffering);
}
