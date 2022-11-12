package com.dentistService.dentist.model.dto;

import com.dentistService.dentist.model.DentistModel;
import lombok.Getter;

@Getter
public class DentistDTO {
    private String name;
    private String cpf;
    private String medicalRegister;

    public DentistModel transformToDentistModel() {
        return new DentistModel(name, cpf, medicalRegister);
    }
}
