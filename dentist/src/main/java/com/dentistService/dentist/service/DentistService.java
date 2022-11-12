package com.dentistService.dentist.service;

import com.dentistService.dentist.model.DentistModel;
import com.dentistService.dentist.repository.DentistRepository;
import com.dentistService.utils.exception.CustomException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class DentistService {
    private final DentistRepository dentistRepository;

    @Autowired
    public DentistService(DentistRepository dentistRepository) {
        this.dentistRepository = dentistRepository;
    }

    public DentistModel create(DentistModel dentistModel) throws CustomException {
        if (dentistModel.getName() == null || dentistModel.getName().equals("") || dentistModel.getName().length() > 300) {
            throw new CustomException("ERRO", "Dentist name is invalid.");
        }
        return dentistRepository.save(dentistModel);
    }

    public List<DentistModel> listAll() {
        return dentistRepository.findAll();
    }

    public DentistModel getById(UUID id) {
        return dentistRepository.findById(id).orElse(null);
    }

    public void deleteById(UUID id) {
        dentistRepository.deleteById(id);
    }

}
