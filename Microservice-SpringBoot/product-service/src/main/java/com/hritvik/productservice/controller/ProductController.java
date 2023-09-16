package com.hritvik.productservice.controller;

import com.hritvik.productservice.model.Product;
import com.hritvik.productservice.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/product")
public class ProductController {
    @Autowired
    ProductService productService;

    @PostMapping("add")
    public void addProduct(@RequestBody Product prod) {
        productService.addProduct(prod);
    }
    @GetMapping("all")
    public List<Product> getAllProduct(){
        return productService.getAllProduct();
    }
    @GetMapping("id")
    public Product getProdById(@RequestParam("id") Long id){
        return productService.getProdById(id);
    }
    @DeleteMapping("delete")
    public String DeleteProduct(@RequestParam("id") Long productId)
    {
        return productService.DeleteProduct(productId);
    }
}
