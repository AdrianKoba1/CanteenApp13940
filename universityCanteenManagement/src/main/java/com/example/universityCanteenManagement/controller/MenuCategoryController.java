package com.example.universityCanteenManagement.controller;

import com.example.universityCanteenManagement.dto.*;
import com.example.universityCanteenManagement.service.MenuCategoryService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/category")
public class MenuCategoryController {

    private final MenuCategoryService categoryService;

    public MenuCategoryController(MenuCategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @PostMapping("/add")
    public ResponseEntity<MenuCategoryResponseDTO> add(@RequestBody MenuCategoryAddRequestDTO dto) {
        return ResponseEntity.ok().body(categoryService.addCategory(dto));
    }

    @GetMapping("/getAll")
    public ResponseEntity<List<MenuCategoryResponseDTO>> getAll() {
        return ResponseEntity.ok(categoryService.getAllCategories());
    }

    @PutMapping("/update")
    public ResponseEntity<MenuCategoryResponseDTO> update(@RequestBody MenuCategoryUpdateRequestDTO dto) {
        return ResponseEntity.ok(categoryService.updateCategory(dto));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<MenuCategoryResponseDTO> delete(@PathVariable Long id) {
        return ResponseEntity.ok(categoryService.deleteCategory(id));
    }
}
