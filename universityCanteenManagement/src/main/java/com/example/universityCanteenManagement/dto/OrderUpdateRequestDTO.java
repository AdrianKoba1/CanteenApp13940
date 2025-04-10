package com.example.universityCanteenManagement.dto;

import com.example.universityCanteenManagement.domain.OrderItem;
import com.example.universityCanteenManagement.enums.OrderStatus;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@RequiredArgsConstructor
public class OrderUpdateRequestDTO {

    private Long id;
    private Long userId;
    private LocalDateTime orderTime;
    private LocalDateTime desiredPickupTime;
    private OrderStatus orderStatus;
    private double totalPrice;
    private List<OrderItem> orderItems;


}
