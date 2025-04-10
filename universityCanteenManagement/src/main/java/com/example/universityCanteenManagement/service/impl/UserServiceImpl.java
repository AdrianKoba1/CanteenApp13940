package com.example.universityCanteenManagement.service.impl;

import com.example.universityCanteenManagement.domain.User;
import com.example.universityCanteenManagement.dto.UserAddRequestDTO;
import com.example.universityCanteenManagement.dto.UserResponseDTO;
import com.example.universityCanteenManagement.dto.UserUpdateRequestDTO;
import com.example.universityCanteenManagement.repository.UserRepository;
import com.example.universityCanteenManagement.service.UserService;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;


@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    @Override
    public UserResponseDTO addUser(UserAddRequestDTO dto) {   // adding new user

        User user = User.builder()
                .username(dto.getUsername())
                .password(dto.getPassword())
                .role(dto.getRole())
                .email(dto.getEmail())
                .createdAt(Timestamp.from(Instant.now()))
                .build();
        userRepository.save(user);

        return toUserResponseDTO(user);
    }

    @Override
    public List<UserResponseDTO> getAllUsers() {   // getting all users from DB

        List<User> userList = userRepository.findAll();

        List<UserResponseDTO> userResponseDTOList = new ArrayList<>();
        for (User user : userList){

            userResponseDTOList.add(toUserResponseDTO(user));

        }
        return userResponseDTOList;
    }

    @Override
    public UserResponseDTO updateUser(UserUpdateRequestDTO dto) {   // updating user

        User user = userRepository.findById(dto.getId())
                .orElseThrow(() -> new RuntimeException("User with the following ID is not found :("));

        userRepository.save(toUser(dto, user));   // from UserUpdateRequestDTO to User type

        return toUserResponseDTO(user);
    }

    @Override
    public UserResponseDTO deleteUser(Long id) {

        User user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User with the following ID is not found :("));

        userRepository.delete(user);   // user is deleted

        return toUserResponseDTO(user);
    }

    public static UserResponseDTO toUserResponseDTO(User user){

        return UserResponseDTO.builder()
                .id(user.getId())
                .username(user.getUsername())
                .password(user.getPassword())
                .role(user.getRole())
                .email(user.getEmail())
                .createdAt(user.getCreatedAt())
                .updatedAt(user.getUpdatedAt())
                .build();
    }

    public static User toUser(UserUpdateRequestDTO dto, User user){

        user.setUsername(dto.getUsername());
        user.setPassword(dto.getPassword());
        user.setRole(dto.getRole());
        user.setEmail(dto.getEmail());
        user.setUpdatedAt(Timestamp.from(Instant.now()));

        return user;
    }
}
