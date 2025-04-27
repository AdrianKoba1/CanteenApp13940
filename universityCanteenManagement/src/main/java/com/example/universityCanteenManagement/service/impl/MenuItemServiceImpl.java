package com.example.universityCanteenManagement.service.impl;

import com.example.universityCanteenManagement.domain.MenuCategory;
import com.example.universityCanteenManagement.domain.MenuItem;
import com.example.universityCanteenManagement.dto.MenuItemAddRequestDTO;
import com.example.universityCanteenManagement.dto.MenuItemResponseDTO;
import com.example.universityCanteenManagement.dto.MenuItemUpdateRequestDTO;
import com.example.universityCanteenManagement.repository.MenuCategoryRepository;
import com.example.universityCanteenManagement.repository.MenuItemRepository;
import com.example.universityCanteenManagement.service.MenuItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MenuItemServiceImpl implements MenuItemService {

    private final MenuCategoryRepository menuCategoryRepository;
    private final MenuItemRepository menuItemRepository;
    @Override
    public MenuItemResponseDTO addMenuItem(MenuItemAddRequestDTO dto) {

        MenuCategory menuCategory = menuCategoryRepository.findById(dto.getCategoryId())
                .orElseThrow(() -> new RuntimeException("MenuCategory with the following ID is not found :("));

        MenuItem menuItem = MenuItem.builder()
                .name(dto.getName())
                .description(dto.getDescription())
                .image(dto.getImage())
                .price(dto.getPrice())
                .available(dto.isAvailable())
                .menuCategory(menuCategory)
                .build();

        menuItemRepository.save(menuItem);
        return toMenuItemResponseDTO(menuItem);
    }

    @Override
    public List<MenuItemResponseDTO> getAllMenuItems() {

        List<MenuItem> menuItemList = menuItemRepository.findAll();

        List<MenuItemResponseDTO> menuItemResponseDTOList = new ArrayList<>();

        for (MenuItem menuItem : menuItemList){

            menuItemResponseDTOList.add(toMenuItemResponseDTO(menuItem));
        }
        return menuItemResponseDTOList;
    }

    @Override
    public MenuItemResponseDTO updateMenuItem(MenuItemUpdateRequestDTO dto) {

        MenuItem menuItem = menuItemRepository.findById(dto.getId())
                .orElseThrow(() -> new RuntimeException("MenuItem with the following ID is not found :("));

        menuItemRepository.save(toMenuItem(dto, menuItem));

        return toMenuItemResponseDTO(menuItem);
    }

    @Override
    public MenuItemResponseDTO deleteMenuItem(Long id) {

        MenuItem menuItem = menuItemRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("MenuItem with the following ID is not found :("));

        menuItemRepository.delete(menuItem);
        return toMenuItemResponseDTO(menuItem);
    }

    public MenuItemResponseDTO toMenuItemResponseDTO(MenuItem menuItem){

        return MenuItemResponseDTO.builder()
                .id(menuItem.getId())
                .name(menuItem.getName())
                .description(menuItem.getDescription())
                .image(menuItem.getImage())
                .price(menuItem.getPrice())
                .available(menuItem.isAvailable())
                .categoryId(menuItem.getMenuCategory().getId())
                .build();
    }
    public MenuItem toMenuItem(MenuItemUpdateRequestDTO dto, MenuItem menuItem){

        MenuCategory menuCategory = menuCategoryRepository.findById(dto.getCategoryId())
                .orElseThrow(() -> new RuntimeException("MenuCategory with the following ID is not found :("));

        menuItem.setName(dto.getName());
        menuItem.setDescription(dto.getDescription());
        menuItem.setImage(dto.getImage());
        menuItem.setAvailability(dto.isAvailable());
        menuItem.setMenuCategory(menuCategory);

        return menuItem;
    }
}
