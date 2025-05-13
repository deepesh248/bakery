package com.bakery.controller;

import com.bakery.dto.OrderRequest;
import com.bakery.model.Order;
import com.bakery.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/orders")
@CrossOrigin(
	    origins = "http://localhost:5173", 
	    allowCredentials = "true"
	) 
public class OrderController {

    @Autowired
    private OrderService orderService;

    @PostMapping
    public ResponseEntity<Order> placeOrder(@RequestBody OrderRequest orderRequest) {
        Order savedOrder = orderService.createOrder(orderRequest);
        return new ResponseEntity<>(savedOrder, HttpStatus.CREATED);
    }
}

