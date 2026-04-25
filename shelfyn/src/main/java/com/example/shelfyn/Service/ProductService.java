package com.example.shelfyn.Service;
import com.example.shelfyn.Entity.Product;
import com.example.shelfyn.mapper.ProductMapper;
import com.example.shelfyn.model.ProductResponse;
import com.example.shelfyn.model.ProductStatus;
import com.example.shelfyn.repository.ProductRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@AllArgsConstructor
public class ProductService {

    private final ProductRepository repository;

    public Product addProduct(Product product) {
        return repository.save(product);
    }

    public List<ProductResponse> getAllProducts() {
        return repository.findAll()
                .stream()
                .map(ProductMapper::toResponse)
                .toList();
    }

    public ProductResponse getProductById(Long id) {
        Product product = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found"));

        return ProductMapper.toResponse(product);
    }

    public Product updateProduct(Long id, Product updated) {
        Product product = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found"));

        product.setName(updated.getName());
        product.setCategory(updated.getCategory());
        product.setPrice(updated.getPrice());
        product.setOpeningDate(updated.getOpeningDate());
        product.setExpiryDate(updated.getExpiryDate());
        product.setFavorite(updated.isFavorite());

        return repository.save(product);
    }

    public void deleteProduct(Long id) {
        repository.deleteById(id);
    }

    public Product toggleFavorite(Long id) {
        Product product = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Not found"));

        product.setFavorite(!product.isFavorite());
        return repository.save(product);
    }
    public Map<String, Long> getStats() {
        List<Product> products = repository.findAll();

        long total = products.size();

        long expired = products.stream()
                .filter(p -> ProductMapper.getStatus(p) == ProductStatus.EXPIRED)
                .count();

        long expiring = products.stream()
                .filter(p -> ProductMapper.getStatus(p) == ProductStatus.EXPIRING_SOON)
                .count();

        long safe = products.stream()
                .filter(p -> ProductMapper.getStatus(p) == ProductStatus.SAFE)
                .count();

        Map<String, Long> stats = new HashMap<>();
        stats.put("total", total);
        stats.put("expired", expired);
        stats.put("expiring", expiring);
        stats.put("safe", safe);

        return stats;
    }
}