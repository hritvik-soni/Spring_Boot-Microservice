package com.hritvik.productservice.repository;

import com.hritvik.productservice.model.Product;
import io.micrometer.common.KeyValues;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IProductRepo extends JpaRepository<Product,Long> {
    List<Product> findBySkuCodeIn(List<String> skuCode);
}
