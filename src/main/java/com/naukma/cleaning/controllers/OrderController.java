package com.naukma.cleaning.controllers;

import com.naukma.cleaning.models.dtos.OrderDto;
import com.naukma.cleaning.models.order.Status;
import com.naukma.cleaning.services.orderService.OrderService;
import com.naukma.cleaning.utils.exceptions.PaymentUnsuccessfulException;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/orders")
@RequiredArgsConstructor
@Tag(name = "Order API", description = "Endpoint for operations with orders")
public class OrderController {

    private final OrderService orderService;

    @Operation(summary = "Get order by id", description = "Get order by id")
    @GetMapping("/{id}")
    public OrderDto getOrder(@PathVariable("id") Long id) {
        return orderService.getOrderDto(id);
    }

    @Operation(summary = "Change order if status NOT_PROCESSED", description = "Change order if status NOT_PROCESSED")
    @PutMapping()
    public void editOrder(@RequestBody @Valid OrderDto orderDto) {
        orderService.editOrder(orderDto);
    }

    @Operation(summary = "Change order status", description = "Change order status")
    @PutMapping("/status")
    public void changeStatus(@RequestBody @Valid OrderDto orderDto, @RequestParam Status status) {
        orderService.changeStatus(orderDto, status);

    }

    @Operation(summary = "Create new order", description = "Create new order")
    @PostMapping()
    public void createOrder(@RequestBody @Valid OrderDto orderDto) {
        orderService.createOrder(orderDto);
    }
}
