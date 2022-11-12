package com.dentistService.dentist.repository;

import com.dentistService.dentist.model.DentistModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface DentistRepository extends JpaRepository<DentistModel, UUID> {
}
