package com.ortolegal.dentistClinic.model.dto;

import com.ortolegal.dentistClinic.model.DentistClinicModel;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import java.util.UUID;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class DentistClinicResponseDTO {
    private UUID id;
    private UUID dentistId;
    private UUID clinicId;

    public static DentistClinicResponseDTO transformToClinicResponseDTO(DentistClinicModel dentistClinicModel) {
        return new DentistClinicResponseDTO(dentistClinicModel.getId(), dentistClinicModel.getDentistId(), dentistClinicModel.getClinicId());
    }
}
