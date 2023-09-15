package com.hritvik.orderservice.service;

import com.hritvik.orderservice.repository.IOrderRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderService {

    @Autowired
    private final IOrderRepo orderRepo;


}
