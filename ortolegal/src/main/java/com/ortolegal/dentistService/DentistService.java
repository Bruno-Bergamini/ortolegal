package com.ortolegal.dentistService;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@FeignClient("dentist-service")
public interface DentistService {
    @GetMapping("/api/dentist")
    ResponseEntity<List<Object>> listAll();

    @PostMapping("/api/dentist")
    ResponseEntity<Object> create();

    @GetMapping("/api/dentist/{id}")
    ResponseEntity<Object> findById();

    @PutMapping("/api/dentist/{id}")
    ResponseEntity<Object> update();

    @DeleteMapping("/api/dentist/{id}")
    ResponseEntity<Object> delete();
}
