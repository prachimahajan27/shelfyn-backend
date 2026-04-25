package com.example.shelfyn.model;

import lombok.Data;

@Data
public class ProductResponse {
    private Long id;
    private String name;
    private String category;
    private Double price;
    private boolean favorite;
    private ProductStatus status;

    // getters setters
}