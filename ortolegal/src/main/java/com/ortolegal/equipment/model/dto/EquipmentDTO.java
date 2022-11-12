package com.ortolegal.equipment.model.dto;

import com.ortolegal.equipment.model.EquipmentModel;
import lombok.Getter;

import java.util.UUID;

@Getter
public class EquipmentDTO {
    private UUID serial;
    private String model;
    private String memory;
    private String processor;
    private String storage;

    public EquipmentModel transformToEquipmentModel() {
        return new EquipmentModel(serial, model, memory, processor, storage);
    }
}
