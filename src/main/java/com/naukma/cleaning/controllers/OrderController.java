package com.naukma.cleaning.controllers;

import com.naukma.cleaning.models.order.OrderDto;
import com.naukma.cleaning.models.order.Status;
import com.naukma.cleaning.services.orderService.OrderService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/orders")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;

    @GetMapping("/{id}")
    public OrderDto getOrder(@PathVariable("id") long id) {
        return orderService.getOrder(id);
    }

    @PostMapping()
    public void createOrder(@RequestBody @Valid OrderDto order) {
        orderService.createOrder(order);
    }

    @PutMapping()
    public void editOrder(@RequestBody @Valid OrderDto order) {
        orderService.editOrder(order);
    }

    @PutMapping()
    public void changeStatus(@RequestBody  @Valid OrderDto order, @RequestParam Status status) {
        orderService.changeStatus(order, status);
    }

}
