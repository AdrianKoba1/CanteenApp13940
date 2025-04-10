package com.example.universityCanteenManagement.dto;

import com.example.universityCanteenManagement.domain.OrderItem;
import com.example.universityCanteenManagement.enums.OrderStatus;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.service.annotation.GetExchange;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@Builder
public class OrderResponseDTO {

    private Long id;
    private Long userId;
    private LocalDateTime orderTime;
    private LocalDateTime desiredPickupTime;
    private OrderStatus orderStatus;
    private double totalPrice;
    private List<OrderItem> orderItems;

}
