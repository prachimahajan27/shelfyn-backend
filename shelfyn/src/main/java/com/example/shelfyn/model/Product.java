package com.example.shelfyn.entity;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "product")
@Lombok
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