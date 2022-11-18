package com.dentistService.dentist.controller;

import com.dentistService.dentist.model.DentistModel;
import com.dentistService.dentist.model.dto.DentistDTO;
import com.dentistService.dentist.model.dto.DentistResponseDTO;
import com.dentistService.dentist.service.DentistService;
import com.dentistService.utils.exception.CustomException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/dentist")
public class DentistController {
    DentistService dentistService;

    @Autowired
    public DentistController(DentistService dentistService) {
        this.dentistService = dentistService;
    }

    @PostMapping
    public ResponseEntity<DentistResponseDTO> create(@RequestBody DentistDTO dto) throws CustomException {
         DentistModel dentistModel = dentistService.create(dto.transformToDentistModel());
        DentistResponseDTO response = DentistResponseDTO.transformToDentistResponseDTO(dentistModel);
        return ResponseEntity.ok(response);
    }

    @GetMapping
    public ResponseEntity<List<DentistResponseDTO>> listAll() {
        List<DentistModel> dentistModels = dentistService.listAll();
        List<DentistResponseDTO> response = dentistModels.stream().map(DentistResponseDTO::transformToDentistResponseDTO).collect(Collectors.toList());
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DentistResponseDTO> findById(@PathVariable UUID id) {
        DentistModel dentistModel = dentistService.getById(id);
        DentistResponseDTO response = DentistResponseDTO.transformToDentistResponseDTO(dentistModel);
        return ResponseEntity.ok(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<DentistResponseDTO> update(@PathVariable UUID id, @RequestBody DentistDTO dto) throws CustomException {
        DentistModel dentistModel = dentistService.getById(id);
        dentistModel.setName(dto.getName());
        dentistModel.setCpf(dto.getCpf());
        dentistModel.setMedicalRegister(dto.getMedicalRegister());

        dentistService.create(dentistModel);
        DentistResponseDTO response = DentistResponseDTO.transformToDentistResponseDTO(dentistModel);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        dentistService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

}
