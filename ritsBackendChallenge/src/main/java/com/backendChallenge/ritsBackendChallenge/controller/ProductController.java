package com.backendChallenge.ritsBackendChallenge.controller;

import com.backendChallenge.ritsBackendChallenge.entities.product.Product;
import com.backendChallenge.ritsBackendChallenge.service.ProductService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/product")
public class ProductController {

    private ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @PostMapping
    public ResponseEntity<Void> saveProduct(@RequestBody Product product) {

        productService.saveProduct(product);

        return ResponseEntity.ok().build();
    }

    @GetMapping
    public ResponseEntity<List<Product>> getAllProducts() {

        var response = productService.getAllProducts();

        return new ResponseEntity<>(response, HttpStatus.OK);

    }

    @PutMapping("/{productId}")
    public ResponseEntity<Void> updateProduct(@PathVariable(name = "productId") Long id,
                                              @RequestBody Product product) {

        productService.updateProduct(id, product);

        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{productId}")
    public ResponseEntity<Void> deleteProduct(@PathVariable(name = "productId") Long id) {

        productService.deleteProduct(id);

        return ResponseEntity.noContent().build();
    }

}
