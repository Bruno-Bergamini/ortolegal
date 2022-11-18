package com.ortolegal.dentistClinic.model;

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
@Table(name = "DENTIST_CLINIC")
public class DentistClinicModel {

    @Id
    @Type(type = "org.hibernate.type.UUIDCharType")
    private UUID id;

    @Column(name = "dentistId", nullable = false, unique = true)
    private UUID dentistId;

    @Column(name = "clinicId", nullable = false)
    private UUID clinicId;

    public DentistClinicModel(UUID dentistId, UUID clinicId) {
        this.dentistId = dentistId;
        this.clinicId = clinicId;
    }

    @PrePersist
    protected void onCreate() {
        setId(UUID.randomUUID());
    }
}
