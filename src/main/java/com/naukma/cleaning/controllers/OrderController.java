package com.naukma.cleaning.controllers;

import com.naukma.cleaning.models.order.Order;
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
    public Order getOrder(@PathVariable("id") Long id) {
        return orderService.getOrder(id);
    }

    @PostMapping()
    public void createOrder(@RequestBody @Valid Order order) {
        orderService.createOrder(order);
    }

    @PutMapping()
    public void editOrder(@RequestBody @Valid Order order) {
        orderService.editOrder(order);
    }

    @PutMapping("/status")
    public void changeStatus(@RequestBody  @Valid Order order, @RequestParam Status status) {
        orderService.changeStatus(order, status);
    }

}
