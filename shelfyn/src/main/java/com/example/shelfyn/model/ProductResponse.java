package com.example.shelfyn.model;

import lombok.Data;

import java.time.LocalDate;

@Data
public class ProductResponse {
    private Long id;
    private String name;
    private String category;
    private Double price;
    private boolean favorite;
    private ProductStatus status;
    private LocalDate expiryDate;

    // getters setters
}