package com.example.universityCanteenManagement.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
public class OrderItemResponseDTO {

    private Long id;
    private Long orderId;
    private Long menuItemId;
    private int quantity;
    private double price;

}
