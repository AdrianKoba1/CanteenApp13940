package com.example.universityCanteenManagement.dto;

import com.example.universityCanteenManagement.enums.Role;
import lombok.*;

import java.sql.Timestamp;

@Getter
@Setter
@Builder
public class UserResponseDTO {

    private Long id;
    private String username;
    private String password;
    private Role role;
    private String email;
    private Timestamp createdAt;
    private Timestamp updatedAt;
}
