package com.example.universityCanteenManagement.controller;

import com.example.universityCanteenManagement.dto.*;
import com.example.universityCanteenManagement.service.OrderService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/order")
public class OrderController {

    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping("/add")
    public ResponseEntity<OrderResponseDTO> add(@RequestBody OrderAddRequestDTO dto) {
        return ResponseEntity.ok().body(orderService.addOrder(dto));
    }

    @GetMapping("/getAll")
    public ResponseEntity<List<OrderResponseDTO>> getAll() {
        return ResponseEntity.ok(orderService.getAllOrders());
    }

    @PutMapping("/update")
    public ResponseEntity<OrderResponseDTO> update(@RequestBody OrderUpdateRequestDTO dto) {
        return ResponseEntity.ok(orderService.updateOrder(dto));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<OrderResponseDTO> delete(@PathVariable Long id) {
        return ResponseEntity.ok(orderService.deleteOrder(id));
    }

}
