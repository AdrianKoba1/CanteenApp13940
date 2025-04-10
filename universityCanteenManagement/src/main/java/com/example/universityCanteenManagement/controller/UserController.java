package com.example.universityCanteenManagement.controller;

import com.example.universityCanteenManagement.dto.UserAddRequestDTO;
import com.example.universityCanteenManagement.dto.UserResponseDTO;
import com.example.universityCanteenManagement.dto.UserUpdateRequestDTO;
import com.example.universityCanteenManagement.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/user")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/add")
    public ResponseEntity<UserResponseDTO> add(@RequestBody UserAddRequestDTO dto) {
        return ResponseEntity.ok().body(userService.addUser(dto));
    }

    @GetMapping("/getAll")
    public ResponseEntity<List<UserResponseDTO>> getAll() {
        return ResponseEntity.ok(userService.getAllUsers());
    }

    @PutMapping("/update")
    public ResponseEntity<UserResponseDTO> update(@RequestBody UserUpdateRequestDTO dto) {
        return ResponseEntity.ok(userService.updateUser(dto));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<UserResponseDTO> delete(@PathVariable Long id) {
        return ResponseEntity.ok(userService.deleteUser(id));
    }
}
