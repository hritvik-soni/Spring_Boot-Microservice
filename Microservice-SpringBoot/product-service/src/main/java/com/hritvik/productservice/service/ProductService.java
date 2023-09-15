package com.hritvik.productservice.service;

import com.hritvik.productservice.repository.IProductRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductService {
    @Autowired
    IProductRepo productRepo;
}
