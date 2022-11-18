package com.ortolegal.dentistService;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@FeignClient("dentist-service")
public interface DentistService {
    @GetMapping("/api/dentist")
    ResponseEntity<List<Object>> listAll();

    @PostMapping("/api/dentist")
    ResponseEntity<Object> create(@RequestBody Object dto);

    @GetMapping("/api/dentist/{id}")
    ResponseEntity<Object> findById(@PathVariable("id") UUID id);

    @PutMapping("/api/dentist/{id}")
    ResponseEntity<Object> update(@PathVariable("id") UUID id, @RequestBody Object dto);

    @DeleteMapping("/api/dentist/{id}")
    ResponseEntity<Object> delete(@PathVariable("id") UUID id);
}
