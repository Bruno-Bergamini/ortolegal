package com.dentistService.dentist.model.dto;

import com.dentistService.dentist.model.DentistModel;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.UUID;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class DentistResponseDTO {
    private UUID id;
    private String name;
    private String cpf;
    private String medicalRegister;

    public static DentistResponseDTO transformToDentistResponseDTO(DentistModel dentistModel) {
        return new DentistResponseDTO(dentistModel.getId(), dentistModel.getName(), dentistModel.getCpf(), dentistModel.getMedicalRegister());
    }
}
