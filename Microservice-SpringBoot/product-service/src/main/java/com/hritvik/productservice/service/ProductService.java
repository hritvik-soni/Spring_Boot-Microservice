package com.hritvik.productservice.service;

import com.hritvik.productservice.dto.InventoryResponse;
import com.hritvik.productservice.model.Product;
import com.hritvik.productservice.repository.IProductRepo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

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

    public void addProduct(Product prod) {
        productRepo.save(prod);
    }

    public List<Product> getAllProduct() {
        return productRepo.findAll();
    }

    public Product getProdById(Long id) {
        Optional<Product> optional= productRepo.findById(id);
        if(optional.isPresent()){
            return optional.get();
        }
        return null;
    }

    public String DeleteProduct(Long productId) {
        if (productRepo.existsById(productId)) {
            productRepo.deleteById(productId);
            return "product deleted";
        } else {
            return "productId does not exist";
        }
    }

}
