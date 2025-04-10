package com.example.universityCanteenManagement.dto;


import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@RequiredArgsConstructor
public class OrderItemAddRequestDTO {

    private Long orderId;
    private Long menuItemId;
    private int quantity;
    private double price;

}
