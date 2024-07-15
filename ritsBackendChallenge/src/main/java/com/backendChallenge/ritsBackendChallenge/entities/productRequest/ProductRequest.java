package com.backendChallenge.ritsBackendChallenge.entities.productRequest;

import com.backendChallenge.ritsBackendChallenge.entities.product.Product;
import com.backendChallenge.ritsBackendChallenge.entities.request.Request;
import jakarta.persistence.*;

@Entity
@Table(name = "tb_product_request")
public class ProductRequest {

    @EmbeddedId
    private ProductRequestId id;

    @ManyToOne
    @MapsId("productId")
    @JoinColumn(name = "product_id")
    private Product product;

    @ManyToOne
    @MapsId("requestId")
    @JoinColumn(name = "request_id")
    private Request request;

    public ProductRequest() {}

    public ProductRequest(ProductRequestId id, Product product, Request request) {
        this.id = id;
        this.product = product;
        this.request = request;
    }

    public ProductRequestId getId() {
        return id;
    }

    public void setId(ProductRequestId id) {
        this.id = id;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Request getRequest() {
        return request;
    }

    public void setRequest(Request request) {
        this.request = request;
    }
}
