package com.ramosdev.appointment.repository;

import com.ramosdev.appointment.model.ServiceOffering;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ServiceOfferingRepository extends JpaRepository<ServiceOffering, UUID> {
}
