package com.example.universityCanteenManagement.service;

import com.example.universityCanteenManagement.dto.OrderItemAddRequestDTO;
import com.example.universityCanteenManagement.dto.OrderItemResponseDTO;
import com.example.universityCanteenManagement.dto.OrderItemUpdateRequestDTO;

import java.util.List;

public interface OrderItemService {
    OrderItemResponseDTO addOrderItem(OrderItemAddRequestDTO dto);

    List<OrderItemResponseDTO> getAllOrderItems();

    OrderItemResponseDTO updateOrderItem(OrderItemUpdateRequestDTO dto);

    OrderItemResponseDTO deleteOrderItem(Long id);
}
