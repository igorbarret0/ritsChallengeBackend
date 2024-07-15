package com.backendChallenge.ritsBackendChallenge.entities.productRequest;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

@Embeddable
public class ProductRequestId {

    @Column(name = "account_id")
    private Long productId;

    @Column(name = "stock_id")
    private Long requestId;

    public ProductRequestId() {}

    public ProductRequestId(Long productId, Long requestId) {
        this.productId = productId;
        this.requestId = requestId;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public Long getRequestId() {
        return requestId;
    }

    public void setRequestId(Long requestId) {
        this.requestId = requestId;
    }
}
