package com.ecommerceapp.e_Commerce_App.Entity;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;
import java.util.List;
@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
@Document(collection = "orders")

public class Order {

    @Id
    @Schema (description = "Unique identifier for the order", example = "AB-345")
    private String id;
    @Schema(description = "User ID associated with the order", example = "user123")
    private String userId;
    @Schema(description = "List of product IDs associated with the order", example = "['product1', 'product2']")
    private List<String> productIds;
    @Schema(description = "Total amount of the order", example = "100.0")
    private double totalAmount;
    @Schema (description = "Order status", example = "Pending")
    private String status;
    @Schema(description = "Date of the order", example = "2023-08-26")
    private LocalDate orderDate;
}

