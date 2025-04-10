package com.example.universityCanteenManagement.service;

import com.example.universityCanteenManagement.dto.MenuCategoryAddRequestDTO;
import com.example.universityCanteenManagement.dto.MenuCategoryResponseDTO;
import com.example.universityCanteenManagement.dto.MenuCategoryUpdateRequestDTO;

import java.util.List;

public interface MenuCategoryService {
    MenuCategoryResponseDTO addCategory(MenuCategoryAddRequestDTO dto);

    List<MenuCategoryResponseDTO> getAllCategories();

    MenuCategoryResponseDTO updateCategory(MenuCategoryUpdateRequestDTO dto);

    MenuCategoryResponseDTO deleteCategory(Long id);
}
