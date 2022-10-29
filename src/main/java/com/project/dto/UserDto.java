package com.project.dto;

import com.project.model.Role;
import lombok.*;

@Setter
@Getter
public class UserDto {
    private String username;
    private String password;
    private String matchingPassword;
    private String email;
    private Role role;
    private Boolean status;

}
