package com.ramosdev.appointment.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;


@Entity
@Table(name = "service_offering")
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class ServiceOffering {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    private String name;

    private BigDecimal value;

    private LocalDateTime createdAt;
}
