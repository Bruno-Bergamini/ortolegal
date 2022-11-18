package com.ortolegal.dentistClinic.model.dto;

import com.ortolegal.dentistClinic.model.DentistClinicModel;
import lombok.Getter;

import java.util.UUID;

@Getter
public class DentistClinicDTO {
    private UUID dentistId;
    private UUID clinicId;


    public DentistClinicModel transformToDentistClinicModel() {
        return new DentistClinicModel(dentistId, clinicId);
    }
}
