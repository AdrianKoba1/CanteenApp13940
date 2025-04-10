package com.example.universityCanteenManagement.controller;

import com.example.universityCanteenManagement.domain.OrderItem;
import com.example.universityCanteenManagement.dto.*;
import com.example.universityCanteenManagement.service.OrderItemService;
import com.example.universityCanteenManagement.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/orderItem")
@RequiredArgsConstructor
public class OrderItemController {

    private final OrderItemService orderItemService;

    @PostMapping("/add")
    public ResponseEntity<OrderItemResponseDTO> add(@RequestBody OrderItemAddRequestDTO dto) {
        return ResponseEntity.ok().body(orderItemService.addOrderItem(dto));
    }

    @GetMapping("/getAll")
    public ResponseEntity<List<OrderItemResponseDTO>> getAll() {
        return ResponseEntity.ok(orderItemService.getAllOrderItems());
    }

    @PutMapping("/update")
    public ResponseEntity<OrderItemResponseDTO> update(@RequestBody OrderItemUpdateRequestDTO dto) {
        return ResponseEntity.ok(orderItemService.updateOrderItem(dto));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<OrderItemResponseDTO> delete(@PathVariable Long id) {
        return ResponseEntity.ok(orderItemService.deleteOrderItem(id));
    }

}
