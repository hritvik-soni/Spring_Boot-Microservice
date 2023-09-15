package com.hritvik.orderservice.controller;

import com.hritvik.orderservice.dto.OrderRequest;
import com.hritvik.orderservice.model.Order;
import com.hritvik.orderservice.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/order")

public class OrderController {

    @Autowired
    private  OrderService orderService;


     @PostMapping("placeOrder")
     @ResponseStatus(HttpStatus.CREATED)
    public String  placeOrder (@RequestBody OrderRequest orderRequest){

         return orderService.placeOrder(orderRequest);
     }

}
