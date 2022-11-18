package com.ortolegal.user.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.UUID;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "users")
public class UserModel {
    @Id
    @Type(type = "org.hibernate.type.UUIDCharType")
    private UUID id;

    @NotBlank(message = "Field email is mandatory")
    @Column(name = "email", nullable = false, unique = true)
    private String email;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @NotBlank(message = "Field password is mandatory")
    @Column(name = "password", nullable = false)
    private String password;

    public UserModel(String email, String password) {
        this.email = email;
        this.password = password;
    }

    @PrePersist
    public void onCreate() {
        setId(UUID.randomUUID());
    }
}
