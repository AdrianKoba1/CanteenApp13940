package com.example.universityCanteenManagement.service.impl;

import com.example.universityCanteenManagement.domain.MenuItem;
import com.example.universityCanteenManagement.domain.Order;
import com.example.universityCanteenManagement.domain.OrderItem;
import com.example.universityCanteenManagement.dto.OrderItemAddRequestDTO;
import com.example.universityCanteenManagement.dto.OrderItemResponseDTO;
import com.example.universityCanteenManagement.dto.OrderItemUpdateRequestDTO;
import com.example.universityCanteenManagement.repository.MenuItemRepository;
import com.example.universityCanteenManagement.repository.OrderItemRepository;
import com.example.universityCanteenManagement.repository.OrderRepository;
import com.example.universityCanteenManagement.service.OrderItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
@RequiredArgsConstructor
public class OrderItemServiceImpl implements OrderItemService {

    private final OrderRepository orderRepository;
    private final OrderItemRepository orderItemRepository;
    private final MenuItemRepository menuItemRepository;

    @Override
    public OrderItemResponseDTO addOrderItem(OrderItemAddRequestDTO dto) {
        Order order = orderRepository.findById(dto.getOrderId())
                .orElseThrow(() -> new RuntimeException("Order with the following ID is not found :("));
        MenuItem menuItem = menuItemRepository.findById(dto.getMenuItemId())
                .orElseThrow(() -> new RuntimeException("MenuItem with the following ID is not found :("));

        OrderItem orderItem = OrderItem.builder()
                .order(order)
                .menuItem(menuItem)
                .quantity(dto.getQuantity())
                .price(dto.getPrice())
                .build();

        orderItemRepository.save(orderItem);

        return toOrderItemResponseDTO(orderItem);
    }

    @Override
    public List<OrderItemResponseDTO> getAllOrderItems() {

        List<OrderItem> orderItemList = orderItemRepository.findAll();

        List<OrderItemResponseDTO> orderItemResponseDTOList = new ArrayList<>();

        for (OrderItem orderItem : orderItemList){

            orderItemResponseDTOList.add(toOrderItemResponseDTO(orderItem));
        }

        return orderItemResponseDTOList;
    }

    @Override
    public OrderItemResponseDTO updateOrderItem(OrderItemUpdateRequestDTO dto) {
        OrderItem orderItem = orderItemRepository.findById(dto.getId())
                .orElseThrow(() -> new RuntimeException("OrderItem with the following ID is not found :("));

        orderItemRepository.save(toOrderItem(dto, orderItem));

        return toOrderItemResponseDTO(orderItem);
    }

    @Override
    public OrderItemResponseDTO deleteOrderItem(Long id) {

        OrderItem orderItem = orderItemRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("OrderItem with the following ID is not found :("));

        orderItemRepository.delete(orderItem);

        return toOrderItemResponseDTO(orderItem);
    }

    public OrderItemResponseDTO toOrderItemResponseDTO(OrderItem orderItem){

        return OrderItemResponseDTO.builder()
                .id(orderItem.getId())
                .orderId(orderItem.getOrder().getId())
                .menuItemId(orderItem.getMenuItem().getId())
                .quantity(orderItem.getQuantity())
                .price(orderItem.getPrice())
                .build();
    }

    public OrderItem toOrderItem(OrderItemUpdateRequestDTO dto, OrderItem orderItem){
        Order order = orderRepository.findById(dto.getOrderId())
                .orElseThrow(() -> new RuntimeException("Order with the following ID is not found :("));
        MenuItem menuItem = menuItemRepository.findById(dto.getMenuItemId())
                .orElseThrow(() -> new RuntimeException("menuItem with the following ID is not found :("));


        orderItem.setOrder(order);
        orderItem.setMenuItem(menuItem);
        orderItem.setQuantity(dto.getQuantity());
        orderItem.setPrice(dto.getPrice());

        return orderItem;
    }
}
