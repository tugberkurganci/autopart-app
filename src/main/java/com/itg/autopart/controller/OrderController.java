package com.itg.autopart.controller;


import com.itg.autopart.requests.CreateOrderRequest;
import com.itg.autopart.responses.GetAllOrderResponse;
import com.itg.autopart.service.OrderService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/v1/orders")
@AllArgsConstructor
public class OrderController {

    private OrderService orderService;

    @GetMapping("/{userId}")
    public List<GetAllOrderResponse> getOrderById(@PathVariable int userId) {
        return orderService.getAll(userId);
    }

    @PostMapping()
    @ResponseStatus(code = HttpStatus.CREATED)
    public void add(@RequestBody() @Valid CreateOrderRequest createOrderRequest) {
        this.orderService.add(createOrderRequest);
    }
}
