package com.example.universityCanteenManagement.service;

import com.example.universityCanteenManagement.dto.OrderAddRequestDTO;
import com.example.universityCanteenManagement.dto.OrderResponseDTO;
import com.example.universityCanteenManagement.dto.OrderUpdateRequestDTO;

import java.util.List;

public interface OrderService {
    OrderResponseDTO addOrder(OrderAddRequestDTO dto);

    List<OrderResponseDTO> getAllOrders();

    OrderResponseDTO updateOrder(OrderUpdateRequestDTO dto);

    OrderResponseDTO deleteOrder(Long id);
}
