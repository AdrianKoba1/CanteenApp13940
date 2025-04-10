package com.example.universityCanteenManagement.service;

import com.example.universityCanteenManagement.dto.UserAddRequestDTO;
import com.example.universityCanteenManagement.dto.UserResponseDTO;
import com.example.universityCanteenManagement.dto.UserUpdateRequestDTO;

import java.util.List;

public interface UserService {
    UserResponseDTO addUser(UserAddRequestDTO dto);

    List<UserResponseDTO> getAllUsers();

    UserResponseDTO updateUser(UserUpdateRequestDTO dto);

    UserResponseDTO deleteUser(Long id);
}
