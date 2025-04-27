package com.example.universityCanteenManagement.dto;

import com.example.universityCanteenManagement.enums.OrderStatus;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
public class OrderAddRequestDTO {


    private Long userId;
    private LocalDateTime desiredPickupTime;
    private OrderStatus orderStatus;
//    private double totalPrice;
    private List<Long> itemIDs;
}
