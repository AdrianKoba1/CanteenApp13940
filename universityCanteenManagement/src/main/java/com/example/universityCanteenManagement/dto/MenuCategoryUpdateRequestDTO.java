package com.example.universityCanteenManagement.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class MenuCategoryUpdateRequestDTO {

    private Long id;
    private String name;
    private String image;
    private Long parentCategoryId;
    private Long branchId;

}
