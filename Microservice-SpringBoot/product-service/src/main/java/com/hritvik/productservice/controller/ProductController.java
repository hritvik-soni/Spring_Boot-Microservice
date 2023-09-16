package com.hritvik.productservice.controller;

import com.hritvik.productservice.model.Product;
import com.hritvik.productservice.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ProductController {
    @Autowired
    ProductService productService;

    @PostMapping("add/product")
    public void addProduct(@RequestBody Product prod) {
        productService.addProduct(prod);
    }
    @GetMapping("product")
    public List<Product> getAllProduct(){
        return productService.getAllProduct();
        }
    @GetMapping("product/id/{id}")
    public Product getProdById(@PathVariable Long id){
        return productService.getProdById(id);
    }
    @DeleteMapping("product")
    public String DeleteProduct(@RequestParam("id") Long productId)
    {
        return productService.DeleteProduct(productId);
    }
}
