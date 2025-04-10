package com.example.universityCanteenManagement.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MenuItemResponseDTO {

    private Long id;
    private String name;
    private String description;
    private double price;
    private boolean available;
    private Long categoryId;

}
