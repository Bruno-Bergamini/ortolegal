package com.ortolegal.equipment.repository;

import com.ortolegal.equipment.model.EquipmentModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface EquipmentRepository extends JpaRepository<EquipmentModel, UUID> {
}
