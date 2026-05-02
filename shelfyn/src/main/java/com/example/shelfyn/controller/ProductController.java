package com.example.shelfyn.controller;


import com.example.shelfyn.Entity.Product;
import com.example.shelfyn.Entity.User;
import com.example.shelfyn.Service.ProductService;
import com.example.shelfyn.model.ProductResponse;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
@CrossOrigin("*")
public class ProductController {

    private final ProductService service;

    public ProductController(ProductService service) {
        this.service = service;
    }

    @PostMapping("/products")
    public Product addProduct(@RequestBody Product product,
                              Authentication auth) {

        User user = (User) auth.getPrincipal();
        return service.addProduct(product, user.getId());
    }

    @GetMapping("/products")
    public List<ProductResponse> getAllProducts(Authentication auth) {

        User user = (User) auth.getPrincipal();
        return service.getAllProducts(user.getId());
    }

    @GetMapping("/products/{id}")
    public ProductResponse getProductById(@PathVariable Long id,
                                          Authentication auth) {

        User user = (User) auth.getPrincipal();
        return service.getProductById(id, user.getId());
    }

    @PutMapping("/products/{id}")
    public Product updateProduct(@PathVariable Long id,
                                 @RequestBody Product product,
                                 Authentication auth) {

        User user = (User) auth.getPrincipal();
        return service.updateProduct(id, user.getId(), product);
    }

    @DeleteMapping("/products/{id}")
    public String deleteProduct(@PathVariable Long id,
                                Authentication auth) {

        User user = (User) auth.getPrincipal();
        service.deleteProduct(id, user.getId());
        return "Deleted successfully";
    }

    @PatchMapping("/products/{id}/favorite")
    public Product toggleFavorite(@PathVariable Long id,
                                  Authentication auth) {

        User user = (User) auth.getPrincipal();
        return service.toggleFavorite(id, user.getId());
    }
}