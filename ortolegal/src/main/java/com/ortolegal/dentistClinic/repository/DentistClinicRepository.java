package com.ortolegal.dentistClinic.repository;

import com.ortolegal.dentistClinic.model.DentistClinicModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface DentistClinicRepository extends JpaRepository<DentistClinicModel, UUID> { }
