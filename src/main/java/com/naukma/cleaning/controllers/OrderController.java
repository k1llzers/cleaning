package com.naukma.cleaning.controllers;

import com.naukma.cleaning.models.order.OrderDto;
import com.naukma.cleaning.models.order.Status;
import com.naukma.cleaning.services.orderService.OrderService;
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
    public void createOrder(@RequestBody OrderDto order) {
        orderService.createOrder(order);
    }

    @PutMapping()
    public void editOrder(@RequestBody OrderDto order) {
        orderService.editOrder(order);
    }

    @PutMapping()
    public void changeStatus(@RequestBody  OrderDto order, @RequestParam Status status) {
        orderService.changeStatus(order, status);
    }

}
