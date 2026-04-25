package com.example.shelfyn.controller;


import com.example.shelfyn.Entity.Product;
import com.example.shelfyn.Service.ProductService;
import com.example.shelfyn.model.ProductResponse;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/products")
@CrossOrigin("*")
public class ProductController {

    private final ProductService service;

    public ProductController(ProductService service) {
        this.service = service;
    }

    @PostMapping
    public Product addProduct(@RequestBody Product product) {
        return service.addProduct(product);
    }

    @GetMapping
    public List<ProductResponse> getAllProducts() {
        return service.getAllProducts();
    }

    @GetMapping("/{id}")
    public ProductResponse getProductById(@PathVariable Long id) {
        return service.getProductById(id);
    }

    @PutMapping("/{id}")
    public Product updateProduct(@PathVariable Long id,
                                 @RequestBody Product product) {
        return service.updateProduct(id, product);
    }

    @DeleteMapping("/{id}")
    public String deleteProduct(@PathVariable Long id) {
        service.deleteProduct(id);
        return "Deleted successfully";
    }

    @PatchMapping("/{id}/favorite")
    public Product toggleFavorite(@PathVariable Long id) {
        return service.toggleFavorite(id);
    }
    @GetMapping("/stats")
    public Map<String, Long> getStats() {
        return service.getStats();
    }
}