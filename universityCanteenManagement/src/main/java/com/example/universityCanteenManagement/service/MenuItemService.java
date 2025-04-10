package com.example.universityCanteenManagement.service;

import com.example.universityCanteenManagement.dto.MenuItemAddRequestDTO;
import com.example.universityCanteenManagement.dto.MenuItemResponseDTO;
import com.example.universityCanteenManagement.dto.MenuItemUpdateRequestDTO;

import java.util.List;

public interface MenuItemService {
    MenuItemResponseDTO addMenuItem(MenuItemAddRequestDTO dto);

    List<MenuItemResponseDTO> getAllMenuItems();

    MenuItemResponseDTO updateMenuItem(MenuItemUpdateRequestDTO dto);

    MenuItemResponseDTO deleteMenuItem(Long id);
}
