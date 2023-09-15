package com.hritvik.productservice.service;

import com.hritvik.productservice.dto.InventoryResponse;
import com.hritvik.productservice.repository.IProductRepo;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Slf4j
public class ProductService {
    @Autowired
    IProductRepo productRepo;

    @Transactional(readOnly = true)
    public List<InventoryResponse> isInStock(List<String> skuCode) {
        log.info("Checking Inventory");
        return productRepo.findBySkuCodeIn(skuCode).stream()
                .map(Product ->
                        InventoryResponse.builder()
                                .skuCode(Product.getSkuCode())
                                .isInStock(Product.getProductQuantity() > 0)
                                .build()
                ).toList();
    }
}
