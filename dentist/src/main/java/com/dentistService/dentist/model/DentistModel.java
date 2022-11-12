package com.dentistService.dentist.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "DENTIST")
public class DentistModel {

    @Id
    @Type(type = "org.hibernate.type.UUIDCharType")
    private UUID id;

    @Column(name = "name", nullable = false, unique = true)
    private String name;

    @Column(name = "cpf", nullable = false)
    private String cpf;

    @Column(name = "medicalRegister", nullable = false)
    private String medicalRegister;

    public DentistModel(String name, String cpf, String medicalRegister) {
        this.name = name;
        this.cpf = cpf;
        this.medicalRegister = medicalRegister;
    }

    @PrePersist
    protected void onCreate() {
        setId(UUID.randomUUID());
    }
}
