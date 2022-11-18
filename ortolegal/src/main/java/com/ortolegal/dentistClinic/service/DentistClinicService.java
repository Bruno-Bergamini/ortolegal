package com.ortolegal.dentistClinic.service;

import com.ortolegal.dentistClinic.model.DentistClinicModel;
import com.ortolegal.dentistClinic.repository.DentistClinicRepository;
import com.ortolegal.dentistService.DentistService;
import com.ortolegal.utils.exception.CustomException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class DentistClinicService {
    private final DentistClinicRepository dentistClinicRepository;
    private final DentistService dentistService;

    @Autowired
    public DentistClinicService(DentistClinicRepository dentistClinicRepository, DentistService dentistService) {
        this.dentistClinicRepository = dentistClinicRepository;
        this.dentistService = dentistService;
    }

    public DentistClinicModel create(DentistClinicModel dentistClinicModel) throws CustomException {
        return dentistClinicRepository.save(dentistClinicModel);
    }
    public DentistClinicModel associateDentistWithClinic(DentistClinicModel dentistClinicModel) throws CustomException {
        try {
            this.dentistService.findById(dentistClinicModel.getDentistId());
            return dentistClinicRepository.save(dentistClinicModel);
        } catch (Exception e){
            throw new CustomException("ERROR", "Dentist not found");
        }
    }

    public List<DentistClinicModel> listAll() {
        return dentistClinicRepository.findAll();
    }

    public DentistClinicModel getById(UUID id) {
        return dentistClinicRepository.findById(id).orElse(null);
    }

    public void deleteById(UUID id) {
        dentistClinicRepository.deleteById(id);
    }

}


