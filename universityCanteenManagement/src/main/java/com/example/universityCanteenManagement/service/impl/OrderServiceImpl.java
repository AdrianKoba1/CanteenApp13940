package com.example.universityCanteenManagement.service.impl;

import com.example.universityCanteenManagement.domain.Order;
import com.example.universityCanteenManagement.domain.OrderItem;
import com.example.universityCanteenManagement.domain.User;
import com.example.universityCanteenManagement.dto.OrderAddRequestDTO;
import com.example.universityCanteenManagement.dto.OrderResponseDTO;
import com.example.universityCanteenManagement.dto.OrderUpdateRequestDTO;
import com.example.universityCanteenManagement.enums.OrderStatus;
import com.example.universityCanteenManagement.repository.OrderItemRepository;
import com.example.universityCanteenManagement.repository.OrderRepository;
import com.example.universityCanteenManagement.repository.UserRepository;
import com.example.universityCanteenManagement.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final UserRepository userRepository;
    private final OrderRepository orderRepository;
    private final OrderItemRepository orderItemRepository;
    @Override
    public OrderResponseDTO addOrder(OrderAddRequestDTO dto) {
        User user = userRepository.findById(dto.getUserId())
                .orElseThrow(() -> new RuntimeException("User with the following ID is not found :("));
        List<OrderItem> orderItemList = orderItemRepository.findAllById(dto.getItemIDs());

        Order order = Order.builder()
                .user(user)
                .orderTime(LocalDateTime.now())
                .desiredPickupTime(dto.getDesiredPickupTime())
                .orderStatus(OrderStatus.PENDING)
                .totalPrice(dto.getTotalPrice())
                .orderItems(orderItemList)
                .build();

        orderRepository.save(order);

        return toOrderResponseDTO(order);
    }

    @Override
    public List<OrderResponseDTO> getAllOrders() {

        List<Order> orderList = orderRepository.findAll();
        List<OrderResponseDTO> orderResponseDTOList = new ArrayList<>();

        for (Order order : orderList){

            orderResponseDTOList.add(toOrderResponseDTO(order));
        }

        return orderResponseDTOList;
    }

    @Override
    public OrderResponseDTO updateOrder(OrderUpdateRequestDTO dto) {

        Order order = orderRepository.findById(dto.getId())
                .orElseThrow(() -> new RuntimeException("User with the following ID is not found :("));

        orderRepository.save(toUser(dto, order));

        return toOrderResponseDTO(order);
    }

    @Override
    public OrderResponseDTO deleteOrder(Long id) {

        Order order = orderRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User with the following ID is not found :("));

        orderRepository.delete(order);

        return toOrderResponseDTO(order);
    }

    public OrderResponseDTO toOrderResponseDTO(Order order){

        return OrderResponseDTO.builder()
                .id(order.getId())
                .userId(order.getUser().getId())
                .orderTime(order.getOrderTime())
                .desiredPickupTime(order.getDesiredPickupTime())
                .orderStatus(order.getOrderStatus())
                .totalPrice(order.getTotalPrice())
                .orderItems(order.getOrderItems())
                .build();
    }
    public Order toUser(OrderUpdateRequestDTO dto, Order order){
        User user = userRepository.findById(dto.getUserId())
                .orElseThrow(() -> new RuntimeException("User with the following ID is not found :("));

        order.setUser(user);
        order.setOrderTime(order.getOrderTime());
        order.setDesiredPickupTime(dto.getDesiredPickupTime());
        order.setOrderStatus(dto.getOrderStatus());
        order.setTotalPrice(dto.getTotalPrice());
        order.setOrderItems(dto.getOrderItems());

        return order;
    }
}
