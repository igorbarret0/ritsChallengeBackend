package com.backendChallenge.ritsBackendChallenge.controller;

import com.backendChallenge.ritsBackendChallenge.entities.product.Product;
import com.backendChallenge.ritsBackendChallenge.factory.productFactory.ListProductFactory;
import com.backendChallenge.ritsBackendChallenge.factory.productFactory.ProductFactory;
import com.backendChallenge.ritsBackendChallenge.service.ProductService;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ProductControllerTest {

    @Mock
    ProductService productService;

    @InjectMocks
    ProductController productController;

    @Captor
    ArgumentCaptor<Product> productArgumentCaptor;

    @Captor
    ArgumentCaptor<Long> productIdArgumentCaptor;


    @Nested
    class saveProduct {

        @Test
        void shouldReturnHttpOk() {

            var product = ProductFactory.build();

            doNothing().when(productService).saveProduct(any());

            var response = productController.saveProduct(product);

            assertEquals(HttpStatus.OK, response.getStatusCode());

            verify(productService, times(1)).saveProduct(product);
        }

        @Test
        void shouldPassCorrectParametersToService() {

            var product = ProductFactory.build();

            doNothing().when(productService).saveProduct(productArgumentCaptor.capture());

            var response = productController.saveProduct(product);

            assertEquals(product.getName(), productArgumentCaptor.getValue().getName());
            assertEquals(product.getPrice(), productArgumentCaptor.getValue().getPrice());
        }

    }

    @Nested
    class getAllProducts {

        @Test
        void shouldReturnHttpOkAndCorrectResponseBody() {

            var allProducts = ListProductFactory.buildWithTwoProducts();

            doReturn(allProducts)
                    .when(productService).getAllProducts();

            var response = productController.getAllProducts();

            assertEquals(HttpStatus.OK, response.getStatusCode());

            assertEquals(allProducts.getFirst().getName(), response.getBody().getFirst().getName());
            assertEquals(allProducts.getFirst().getPrice(), response.getBody().getFirst().getPrice());

            assertEquals(allProducts.getLast().getName(), response.getBody().getLast().getName());
            assertEquals(allProducts.getLast().getPrice(), response.getBody().getLast().getPrice());

        }

    }

    @Nested
    class UpdateProduct {

        @Test
        void shouldReturnHttpOk() {

            var productId = 1L;
            var product = ProductFactory.build();

            doNothing()
                    .when(productService).updateProduct(anyLong(), any());


            var response = productController.updateProduct(productId, product);

            assertEquals(HttpStatus.OK, response.getStatusCode());

            verify(productService, times(1)).updateProduct(productId, product);
        }

        @Test
        void shouldPassCorrectParametersToService() {

            var productId = 1L;
            var product = ProductFactory.build();

            doNothing()
                    .when(productService).updateProduct(productIdArgumentCaptor.capture(), productArgumentCaptor.capture());

            var response = productController.updateProduct(productId, product);

            assertEquals(productIdArgumentCaptor.getValue(), productId);
            assertEquals(productArgumentCaptor.getValue().getName(), product.getName());
            assertEquals(productArgumentCaptor.getValue().getPrice(), product.getPrice());

        }

    }

    @Nested
    class DeleteProduct {

        @Test
        void shouldReturnHttpNoContent() {

            var productId = 1L;

            doNothing()
                    .when(productService).deleteProduct(anyLong());

            var response = productController.deleteProduct(productId);

            assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
            verify(productService, times(1)).deleteProduct(productId);

        }

        @Test
        void shouldPassCorrectParametersToService() {

            var productId = 1L;

            doNothing()
                    .when(productService).deleteProduct(productIdArgumentCaptor.capture());

            var response = productController.deleteProduct(productId);

            assertEquals(productIdArgumentCaptor.getValue(), productId);

        }

    }

}
