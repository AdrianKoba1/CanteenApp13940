package com.example.universityCanteenManagement.controller;

import com.example.universityCanteenManagement.dto.*;
import com.example.universityCanteenManagement.service.MenuItemService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/menuItem")
public class MenuItemController {

    private final MenuItemService menuItemService;

    public MenuItemController(MenuItemService menuItemService) {
        this.menuItemService = menuItemService;
    }

    @PostMapping("/add")
    public ResponseEntity<MenuItemResponseDTO> add(@RequestBody MenuItemAddRequestDTO dto) {
        return ResponseEntity.ok().body(menuItemService.addMenuItem(dto));
    }

    @GetMapping("/getAll")
    public ResponseEntity<List<MenuItemResponseDTO>> getAll() {
        return ResponseEntity.ok(menuItemService.getAllMenuItems());
    }

    @PutMapping("/update")
    public ResponseEntity<MenuItemResponseDTO> update(@RequestBody MenuItemUpdateRequestDTO dto) {
        return ResponseEntity.ok(menuItemService.updateMenuItem(dto));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<MenuItemResponseDTO> delete(@PathVariable Long id) {
        return ResponseEntity.ok(menuItemService.deleteMenuItem(id));
    }
}
