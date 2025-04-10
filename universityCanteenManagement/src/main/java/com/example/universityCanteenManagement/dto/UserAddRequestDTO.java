package com.example.universityCanteenManagement.dto;

import com.example.universityCanteenManagement.enums.Role;
import lombok.*;

@Getter
@Setter
@Builder
public class UserAddRequestDTO {

    private String username;
    private String password;
    private Role role;
    private String email;

}
