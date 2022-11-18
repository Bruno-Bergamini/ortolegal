package com.ortolegal.user.model.dto;

import com.ortolegal.user.model.UserModel;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.UUID;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class UserResponseDTO {
    private UUID id;
    private String email;

    public static UserResponseDTO transformToUserResponseDTO(UserModel user) {
        return new UserResponseDTO(user.getId(), user.getEmail());
    }
}
