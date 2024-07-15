package com.backendChallenge.ritsBackendChallenge.repository;

import com.backendChallenge.ritsBackendChallenge.entities.productRequest.ProductRequest;
import com.backendChallenge.ritsBackendChallenge.entities.productRequest.ProductRequestId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRequestRepository extends JpaRepository<ProductRequest, ProductRequestId> {
}
