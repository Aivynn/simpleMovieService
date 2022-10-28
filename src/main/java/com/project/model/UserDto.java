package com.project.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {
    private Long id;
    private String username;
    private String password;
    private String matchingPassword;
    private String email;
    private String phone;
    private String firstName;
    private String lastName;
    private String city;
    private Integer age;
    private Role role;
}
