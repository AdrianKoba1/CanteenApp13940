package com.example.universityCanteenManagement.service.impl;

import com.example.universityCanteenManagement.domain.Branch;
import com.example.universityCanteenManagement.domain.MenuCategory;
import com.example.universityCanteenManagement.dto.MenuCategoryAddRequestDTO;
import com.example.universityCanteenManagement.dto.MenuCategoryResponseDTO;
import com.example.universityCanteenManagement.dto.MenuCategoryUpdateRequestDTO;
import com.example.universityCanteenManagement.repository.BranchRepository;
import com.example.universityCanteenManagement.repository.MenuCategoryRepository;
import com.example.universityCanteenManagement.service.MenuCategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MenuCategoryServiceImpl implements MenuCategoryService {

    private final MenuCategoryRepository categoryRepository;
    private final BranchRepository branchRepository;
    @Override
    public MenuCategoryResponseDTO addCategory(MenuCategoryAddRequestDTO dto) {
        MenuCategory category = new MenuCategory();

        if (dto.getParentCategoryId() == null) {

            Branch branch = branchRepository.findById(dto.getBranchId())
                    .orElseThrow(() -> new RuntimeException("Branch with the following ID is not found :("));

            category.setName(dto.getName());
            category.setImage(dto.getImage());
            category.setBranch(branch);
        }else {
            MenuCategory parentCategory = categoryRepository.findById(dto.getParentCategoryId())
                    .orElseThrow(() -> new RuntimeException("ParentCategory with the following ID is not found :("));

            Branch branch = branchRepository.findById(dto.getBranchId())
                    .orElseThrow(() -> new RuntimeException("Branch with the following ID is not found :("));
            category.setName(dto.getName());
            category.setImage(dto.getImage());
            category.setParentCategory(parentCategory);
            category.setBranch(branch);

        }

        categoryRepository.save(category);

        return toCategoryResponseDTO(category);
    }

    @Override
    public List<MenuCategoryResponseDTO> getAllCategories() {
        List<MenuCategory> categoryList = categoryRepository.findAll();

        List<MenuCategoryResponseDTO> categoryResponseDTOList = new ArrayList<>();

        for (MenuCategory category : categoryList){

            categoryResponseDTOList.add(toCategoryResponseDTO(category));
        }

        return categoryResponseDTOList;
    }

    @Override
    public MenuCategoryResponseDTO updateCategory(MenuCategoryUpdateRequestDTO dto) {

        MenuCategory category = categoryRepository.findById(dto.getId())
                .orElseThrow(() -> new RuntimeException("Category with the following ID is not found :("));

        toCategory(dto, category);   // from CategoryUpdateDTO to Category type
        categoryRepository.save(category);

        return toCategoryResponseDTO(category);
    }

    @Override
    public MenuCategoryResponseDTO deleteCategory(Long id) {

        MenuCategory category = categoryRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Category with the following ID is not found :("));

        categoryRepository.delete(category);

        return toCategoryResponseDTO(category);
    }

    public MenuCategoryResponseDTO toCategoryResponseDTO(MenuCategory category){

        return MenuCategoryResponseDTO.builder()
                .id(category.getId())
                .name(category.getName())
                .image(category.getImage())
                .categories(category.getCategories())
                .branchId(category.getBranch().getId())
                .build();
    }
    public MenuCategory toCategory(MenuCategoryUpdateRequestDTO dto, MenuCategory category){

        MenuCategory parentCategory = categoryRepository.findById(dto.getParentCategoryId())
                .orElseThrow(() -> new RuntimeException("ParentCategory with the following ID is not found :("));

        Branch branch = branchRepository.findById(dto.getBranchId())
                .orElseThrow(() -> new RuntimeException("branch with the following ID is not found :("));

        category.setName(dto.getName());
        category.setImage(dto.getImage());
        category.setParentCategory(parentCategory);
        category.setBranch(branch);

        return category;
    }

}
