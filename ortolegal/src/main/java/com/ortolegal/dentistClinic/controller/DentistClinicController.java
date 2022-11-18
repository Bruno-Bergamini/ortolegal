package com.ortolegal.dentistClinic.controller;

import com.ortolegal.dentistClinic.model.DentistClinicModel;
import com.ortolegal.dentistClinic.model.dto.DentistClinicDTO;
import com.ortolegal.dentistClinic.model.dto.DentistClinicResponseDTO;
import com.ortolegal.dentistClinic.service.DentistClinicService;
import com.ortolegal.utils.exception.CustomException;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@SecurityScheme(name = "Bearer", type = SecuritySchemeType.HTTP, bearerFormat = "jwt", scheme = "bearer")
@SecurityRequirement(name = "Bearer")
@RestController
@RequestMapping("/api/dentist-clinic")
public class DentistClinicController {
    DentistClinicService dentistClinicService;
    @Autowired
    public DentistClinicController(DentistClinicService dentistClinicService, AmqpTemplate queueSender) {
        this.dentistClinicService = dentistClinicService;
        this.queueSender = queueSender;
    }
    private final AmqpTemplate queueSender;

    @PostMapping("/associate-dentist-with-clinic")
    public ResponseEntity<DentistClinicResponseDTO> associateDentistWithClinic(@RequestBody DentistClinicDTO dto) throws CustomException {
        DentistClinicModel dentistClinicModel = dentistClinicService.associateDentistWithClinic(dto.transformToDentistClinicModel());
        DentistClinicResponseDTO response = DentistClinicResponseDTO.transformToClinicResponseDTO(dentistClinicModel);
        queueSender.convertAndSend("ortolegal-exchange", "ortolegal-routing-key", "Create dentistClinics");
        return ResponseEntity.ok(response);
    }

    @GetMapping
    public ResponseEntity<List<DentistClinicResponseDTO>> listAll() {
        List<DentistClinicModel> dentistClinicModels = dentistClinicService.listAll();
        List<DentistClinicResponseDTO> response = dentistClinicModels.stream().map(DentistClinicResponseDTO::transformToClinicResponseDTO).collect(Collectors.toList());
        queueSender.convertAndSend("ortolegal-exchange", "ortolegal-routing-key", "List dentistClinics");
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DentistClinicResponseDTO> findById(@PathVariable UUID id) {
        DentistClinicModel dentistClinicModel = dentistClinicService.getById(id);
        DentistClinicResponseDTO response = DentistClinicResponseDTO.transformToClinicResponseDTO(dentistClinicModel);
        queueSender.convertAndSend("ortolegal-exchange", "ortolegal-routing-key", "List by id dentistClinics");
        return ResponseEntity.ok(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<DentistClinicResponseDTO> update(@PathVariable UUID id, @RequestBody DentistClinicDTO dto) throws CustomException {
        DentistClinicModel dentistClinicModel = dentistClinicService.getById(id);
        dentistClinicModel.setClinicId(dto.getClinicId());
        dentistClinicModel.setDentistId(dto.getDentistId());

        dentistClinicService.create(dentistClinicModel);
        DentistClinicResponseDTO response = DentistClinicResponseDTO.transformToClinicResponseDTO(dentistClinicModel);
        queueSender.convertAndSend("ortolegal-exchange", "ortolegal-routing-key", "Edit dentistClinics");
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        dentistClinicService.deleteById(id);
        queueSender.convertAndSend("ortolegal-exchange", "ortolegal-routing-key", "Delete dentistClinics");
        return ResponseEntity.noContent().build();
    }

}
