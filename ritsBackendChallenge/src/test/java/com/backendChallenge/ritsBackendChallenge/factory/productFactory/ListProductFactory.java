package com.backendChallenge.ritsBackendChallenge.factory.productFactory;

import com.backendChallenge.ritsBackendChallenge.entities.product.Product;
import com.backendChallenge.ritsBackendChallenge.entities.productRequest.ProductRequest;

import java.util.List;

public class ListProductFactory {

    public static List<Product> buildWithTwoProducts() {

        var product1 = new Product(1L, "name1", 100);
        var product2 = new Product(2L, "name2", 200);

        return List.of(product1, product2);
    }

}
