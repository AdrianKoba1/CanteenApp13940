package com.example.universityCanteenManagement.dto;

import com.example.universityCanteenManagement.domain.MenuCategory;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
@Builder
public class MenuCategoryResponseDTO {

    private Long id;
    private String name;
    private String image;
    private Set<MenuCategory> categories;
    private Long branchId;
}
