package com.ortolegal.clinic.service;

import com.ortolegal.clinic.model.ClinicModel;
import com.ortolegal.clinic.repository.ClinicRepository;
import com.ortolegal.utils.exception.CustomException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class ClinicService {
    private final ClinicRepository clinicRepository;

    @Autowired
    public ClinicService(ClinicRepository clinicRepository) {
        this.clinicRepository = clinicRepository;
    }

    public ClinicModel create(ClinicModel clinicModel) throws CustomException {
        if (clinicModel.getModel() == null || clinicModel.getModel().equals("") || clinicModel.getModel().length() > 300) {
            throw new CustomException("ERROR", "Clinic model is wrong.");
        }
        return clinicRepository.save(clinicModel);
    }

    public List<ClinicModel> listAll() {
        return clinicRepository.findAll();
    }

    public ClinicModel getById(UUID id) {
        return clinicRepository.findById(id).orElse(null);
    }

    public void deleteById(UUID id) {
        clinicRepository.deleteById(id);
    }

}


