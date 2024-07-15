package com.backendChallenge.ritsBackendChallenge.repository;

import com.backendChallenge.ritsBackendChallenge.entities.product.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    Product findByName(String name);

}
