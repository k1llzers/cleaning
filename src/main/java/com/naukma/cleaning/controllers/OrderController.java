package com.naukma.cleaning.controllers;

import com.naukma.cleaning.models.dtos.OrderDto;
import com.naukma.cleaning.models.order.Status;
import com.naukma.cleaning.services.orderService.OrderService;
import com.naukma.cleaning.utils.exceptions.PaymentUnsuccessfulException;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/orders")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;

    @GetMapping("/{id}")
    public OrderDto getOrder(@PathVariable("id") Long id) {
        throw new PaymentUnsuccessfulException();
        //return orderService.getOrderDto(id);
    }

    @PostMapping()
    public void createOrder(@RequestBody @Valid OrderDto orderDto) {
        orderService.createOrder(orderDto);
    }

    @PutMapping()
    public void editOrder(@RequestBody @Valid OrderDto orderDto) {
        orderService.editOrder(orderDto);
    }

    @PutMapping("/status")
    public void changeStatus(@RequestBody  @Valid OrderDto orderDto, @RequestParam Status status) {
        orderService.changeStatus(orderDto, status);
    }

}
