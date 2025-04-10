package com.example.universityCanteenManagement.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MenuItemUpdateRequestDTO {

    private Long id;
    private String name;
    private String description;
    private double price;
    private boolean available;
    private Long categoryId;

}
