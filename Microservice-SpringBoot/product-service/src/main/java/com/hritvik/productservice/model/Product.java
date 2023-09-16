package com.hritvik.productservice.model;

import com.hritvik.productservice.model.enums.ProductCategory;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "product_table")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY )
    private Long productId;
    private String productName;
    private  String productDescription;
    private ProductCategory productCategory;
    private BigDecimal productPrice;
    private Integer productQuantity;
    private String skuCode;

}
