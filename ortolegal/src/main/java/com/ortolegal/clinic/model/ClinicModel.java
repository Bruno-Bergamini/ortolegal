package com.ortolegal.clinic.model;

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
@Table(name = "CLINIC")
public class ClinicModel {

    @Id
    @Type(type = "org.hibernate.type.UUIDCharType")
    private UUID id;

    @Column(name = "name", nullable = false, unique = true)
    private String name;

    @Column(name = "address", nullable = false)
    private String address;

    public ClinicModel(String name, String address) {
        this.name = name;
        this.address = address;
    }

    @PrePersist
    protected void onCreate() {
        setId(java.util.UUID.randomUUID());
    }
}
