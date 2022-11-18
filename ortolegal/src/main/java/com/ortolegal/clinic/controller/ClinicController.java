package com.ortolegal.clinic.controller;

import com.ortolegal.clinic.model.ClinicModel;
import com.ortolegal.clinic.model.dto.ClinicDTO;
import com.ortolegal.clinic.model.dto.ClinicResponseDTO;
import com.ortolegal.clinic.service.ClinicService;
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
@RequestMapping("/api/clinic")
public class ClinicController {
    ClinicService clinicService;
    @Autowired
    public ClinicController(ClinicService clinicService, AmqpTemplate queueSender) {
        this.clinicService = clinicService;
        this.queueSender = queueSender;
    }
    private final AmqpTemplate queueSender;

    @PostMapping
    public ResponseEntity<ClinicResponseDTO> create(@RequestBody ClinicDTO dto) throws CustomException {
        ClinicModel clinicModel = clinicService.create(dto.transformToClinicModel());
        ClinicResponseDTO response = ClinicResponseDTO.transformToClinicResponseDTO(clinicModel);
        queueSender.convertAndSend("ortolegal-exchange", "ortolegal-routing-key", "Create clinics");
        return ResponseEntity.ok(response);
    }

    @GetMapping
    public ResponseEntity<List<ClinicResponseDTO>> listAll() {
        List<ClinicModel> clinicModels = clinicService.listAll();
        List<ClinicResponseDTO> response = clinicModels.stream().map(ClinicResponseDTO::transformToClinicResponseDTO).collect(Collectors.toList());
        queueSender.convertAndSend("ortolegal-exchange", "ortolegal-routing-key", "List clinics");
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ClinicResponseDTO> findById(@PathVariable UUID id) {
        ClinicModel clinicModel = clinicService.getById(id);
        ClinicResponseDTO response = ClinicResponseDTO.transformToClinicResponseDTO(clinicModel);
        queueSender.convertAndSend("ortolegal-exchange", "ortolegal-routing-key", "List by id clinics");
        return ResponseEntity.ok(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ClinicResponseDTO> update(@PathVariable UUID id, @RequestBody ClinicDTO dto) throws CustomException {
        ClinicModel clinicModel = clinicService.getById(id);
        clinicModel.setName(dto.getName());
        clinicModel.setAddress(dto.getAddress());

        clinicService.create(clinicModel);
        ClinicResponseDTO response = ClinicResponseDTO.transformToClinicResponseDTO(clinicModel);
        queueSender.convertAndSend("ortolegal-exchange", "ortolegal-routing-key", "Edit clinics");
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        clinicService.deleteById(id);
        queueSender.convertAndSend("ortolegal-exchange", "ortolegal-routing-key", "Delete clinics");
        return ResponseEntity.noContent().build();
    }

}
