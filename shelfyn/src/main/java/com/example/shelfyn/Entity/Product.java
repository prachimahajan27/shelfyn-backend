package com.example.shelfyn.Entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

@Data
@Entity
@Table(name = "product")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String category;
    private Double price;

    @Column(name = "opening_date")
    private LocalDate openingDate;

    @Column(name = "expiry_date")
    private LocalDate expiryDate;

    @Column(name = "is_favorite")
    private boolean isFavorite = false;


}