package com.hritvik.orderservice.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name="Order_table")
public class Orders {

    @GeneratedValue(strategy = GenerationType.UUID)
    private String orderId;
    private List<String> skuCodeId;

}
