package com.backendChallenge.ritsBackendChallenge.service;

import com.backendChallenge.ritsBackendChallenge.entities.product.Product;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ProductService {

    void saveProduct(Product product);

    List<Product> getAllProducts();

    void updateProduct(Long productId, Product product);

    void deleteProduct(Long id);

}
