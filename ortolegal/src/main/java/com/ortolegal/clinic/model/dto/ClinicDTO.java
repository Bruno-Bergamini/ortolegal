package com.ortolegal.clinic.model.dto;

import com.ortolegal.clinic.model.ClinicModel;
import lombok.Getter;

import java.util.UUID;

@Getter
public class ClinicDTO {
    private UUID serial;
    private String model;
    private String memory;
    private String processor;
    private String storage;

    public ClinicModel transformToClinicModel() {
        return new ClinicModel(serial, model, memory, processor, storage);
    }
}
