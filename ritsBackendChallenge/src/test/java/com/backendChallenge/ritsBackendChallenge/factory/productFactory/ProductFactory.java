package com.backendChallenge.ritsBackendChallenge.factory.productFactory;

import com.backendChallenge.ritsBackendChallenge.entities.product.Product;

public class ProductFactory {

    public static Product build() {

        return new Product(1L,"name", 100.0);

    }

}
