package com.backendChallenge.ritsBackendChallenge.service.impl;

import com.backendChallenge.ritsBackendChallenge.entities.product.Product;
import com.backendChallenge.ritsBackendChallenge.repository.ProductRepository;
import com.backendChallenge.ritsBackendChallenge.service.ProductService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    private ProductRepository productRepository;

    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }


    @Override
    public void saveProduct(Product product) {

        productRepository.save(product);
    }

    @Override
    public List<Product> getAllProducts() {

        var allProducts = productRepository.findAll();

        return allProducts;
    }

    @Override
    public void updateProduct(Long productId, Product product) {

        var productFound = productRepository.findById(productId)
                .orElseThrow(() -> null);

        productFound.setName(product.getName());
        productFound.setPrice(product.getPrice());

        productRepository.save(productFound);

    }

    @Override
    public void deleteProduct(Long id) {

        productRepository.deleteById(id);

    }
}
