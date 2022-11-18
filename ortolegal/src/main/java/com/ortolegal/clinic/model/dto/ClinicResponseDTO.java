package com.ortolegal.clinic.model.dto;

import com.ortolegal.clinic.model.ClinicModel;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.UUID;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class ClinicResponseDTO {
    private UUID id;
    private String name;
    private String address;

    public static ClinicResponseDTO transformToClinicResponseDTO(ClinicModel clinicModel) {
        return new ClinicResponseDTO(clinicModel.getId(), clinicModel.getName(), clinicModel.getAddress());
    }
}
