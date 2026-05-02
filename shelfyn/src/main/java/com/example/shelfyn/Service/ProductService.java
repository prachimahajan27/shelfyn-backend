package com.example.shelfyn.Service;
import com.example.shelfyn.Entity.Product;
import com.example.shelfyn.Entity.User;
import com.example.shelfyn.mapper.ProductMapper;
import com.example.shelfyn.model.ProductResponse;
import com.example.shelfyn.model.ProductStatus;
import com.example.shelfyn.repository.ProductRepository;
import com.example.shelfyn.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@AllArgsConstructor
public class ProductService {

    private final ProductRepository repository;

    private final UserRepository userRepository;

    public Product addProduct(Product product , Long userid) {
        User user = userRepository.findById(userid).orElseThrow(() -> new RuntimeException("User not found"));
        product.setUser(user);
        return repository.save(product);
    }

    public List<ProductResponse> getAllProducts(Long userid ) {
         userRepository.findById(userid).orElseThrow();


        return repository.findByUserId(userid)
                .stream()
                .map(ProductMapper::toResponse)
                .toList();
    }

    public ProductResponse getProductById(Long id , Long userid) {
        User user = userRepository.findById(userid).orElseThrow(() -> new RuntimeException("User not found"));

        Product product = repository.findByIdAndUserId(id,userid).orElseThrow(() -> new RuntimeException("Product not found"));;



        return ProductMapper.toResponse(product);
    }

    public Product updateProduct(Long id, Long userid,Product updated) {
        Product product = repository.findByIdAndUserId(id,userid)
                .orElseThrow(() -> new RuntimeException("Product not found"));

        product.setName(updated.getName());
        product.setCategory(updated.getCategory());
        product.setPrice(updated.getPrice());
        product.setOpeningDate(updated.getOpeningDate());
        product.setExpiryDate(updated.getExpiryDate());
        product.setFavorite(updated.isFavorite());

        return repository.save(product);
    }

    public void deleteProduct(Long id , Long userid) {
        repository.findByIdAndUserId(id,userid)
                .orElseThrow(() -> new RuntimeException("Product not found"));
        repository.deleteByIdAndUserId(id,userid);
    }

    public Product toggleFavorite(Long id , Long userid) {
        Product product = repository.findByIdAndUserId(id,userid)
                .orElseThrow(() -> new RuntimeException("Product not found"));

        product.setFavorite(!product.isFavorite());
        return repository.save(product);
    }
    public Map<String, Long> getStats(Long userid) {
        List<Product> products = repository.findByUserId(userid);

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
        stats.put("expiringSoon", expiring);
        stats.put("safe", safe);

        return stats;
    }
}