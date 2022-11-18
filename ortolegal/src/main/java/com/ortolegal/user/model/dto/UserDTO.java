package com.ortolegal.user.model.dto;

import com.ortolegal.user.model.UserModel;
import lombok.Getter;

@Getter
public class UserDTO {
    private String email;

    private String password;


    public UserModel transformToUserModel() {
        return new UserModel(email, password);
    }
}
