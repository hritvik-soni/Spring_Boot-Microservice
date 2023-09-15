package com.hritvik.productservice.model;

import com.hritvik.productservice.model.enums.ProductCategory;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "product_table")
public class Product {
    @GeneratedValue(strategy = GenerationType.UUID)
    private String productId;
    private String productName;
    private  String productDescription;
    private ProductCategory productCategory;
    private Long productPrice;
    private Integer productQuantity;
    private String SqCode;

}
