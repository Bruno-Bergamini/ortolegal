package com.ortolegal.clinic.model.dto;

import com.ortolegal.clinic.model.ClinicModel;
import lombok.Getter;

import java.util.UUID;

@Getter
public class ClinicDTO {
    private String name;
    private String address;

    public ClinicModel transformToClinicModel() {
        return new ClinicModel(name, address);
    }
}
