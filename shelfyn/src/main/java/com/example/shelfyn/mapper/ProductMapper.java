package com.example.shelfyn.mapper;
import com.example.shelfyn.Entity.Product;
import com.example.shelfyn.model.ProductStatus;
import com.example.shelfyn.model.ProductResponse;

import java.time.LocalDate;

public class ProductMapper {

    public static ProductResponse toResponse(Product product) {
        ProductResponse res = new ProductResponse();

        res.setId(product.getId());
        res.setName(product.getName());
        res.setCategory(product.getCategory());
        res.setPrice(product.getPrice());
        res.setFavorite(product.isFavorite());
        res.setStatus(getStatus(product));
        res.setExpiryDate(product.getExpiryDate());

        return res;
    }

    public static ProductStatus getStatus(Product product) {
        if (product.getExpiryDate() == null) return ProductStatus.UNKNOWN;

        LocalDate today = LocalDate.now();

        if (today.isAfter(product.getExpiryDate())) {
            return ProductStatus.EXPIRED;
        } else if (!today.plusDays(7).isBefore(product.getExpiryDate())) {
            return ProductStatus.EXPIRING_SOON;
        } else {
            return ProductStatus.SAFE;
        }
    }
}