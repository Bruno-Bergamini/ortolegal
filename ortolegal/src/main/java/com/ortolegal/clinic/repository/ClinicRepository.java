package com.ortolegal.clinic.repository;

import com.ortolegal.clinic.model.ClinicModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ClinicRepository extends JpaRepository<ClinicModel, UUID> {
}
