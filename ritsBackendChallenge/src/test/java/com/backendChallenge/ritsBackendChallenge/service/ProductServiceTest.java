package com.backendChallenge.ritsBackendChallenge.service;

import com.backendChallenge.ritsBackendChallenge.entities.product.Product;
import com.backendChallenge.ritsBackendChallenge.factory.productFactory.ListProductFactory;
import com.backendChallenge.ritsBackendChallenge.factory.productFactory.ProductFactory;
import com.backendChallenge.ritsBackendChallenge.repository.ProductRepository;
import com.backendChallenge.ritsBackendChallenge.service.impl.ProductServiceImpl;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ProductServiceTest {

    @Mock
    ProductRepository productRepository;

    @InjectMocks
    ProductServiceImpl productService;

    @Captor
    ArgumentCaptor<Product> productArgumentCaptor;

    @Captor
    ArgumentCaptor<Long> productIdArgumentCaptor;

    @Nested
    class SaveProduct {

        @Test
        void shouldSaveAnProduct() {

            var product = ProductFactory.build();

            doReturn(product)
                    .when(productRepository).save(productArgumentCaptor.capture());

            productService.saveProduct(product);

            assertEquals(product.getName(), productArgumentCaptor.getValue().getName());
            assertEquals(product.getPrice(), productArgumentCaptor.getValue().getPrice());
            verify(productRepository, times(1)).save(product);

        }

    }

    @Nested
    class GetAllProducts {

        @Test
        void shouldReturnAllProductsCorrectly() {

            var allProducts = ListProductFactory.buildWithTwoProducts();

            doReturn(allProducts)
                    .when(productRepository).findAll();

            var response = productService.getAllProducts();

            assertEquals(allProducts.getFirst().getName(), response.getFirst().getName());
            assertEquals(allProducts.getFirst().getPrice(), response.getFirst().getPrice());
            verify(productRepository, times(1)).findAll();

        }

    }

    @Nested
    class UpdateProduct {

        @Test
        void shouldUpdateAnProductCorrectly() {

            var productId = 1L;
            var product = ProductFactory.build();

            doReturn(Optional.of(product))
                    .when(productRepository).findById(productIdArgumentCaptor.capture());

            product.setName("new name");
            product.setPrice(90.0);

            doReturn(product).when(productRepository).save(productArgumentCaptor.capture());

            productService.updateProduct(productId, product);

            assertEquals(product.getName(), productArgumentCaptor.getValue().getName());
            assertEquals(product.getPrice(), productArgumentCaptor.getValue().getPrice());
            verify(productRepository, times(1)).findById(productId);
            verify(productRepository, times(1)).save(product);

        }

        @Test
        void shouldThrowExceptionWhenAProductCouldNotBeFound() {

            var productId = 99L;
            var product = ProductFactory.build();

            doReturn(Optional.empty())
                    .when(productRepository).findById(productId);

            Exception exception =
                    assertThrows(RuntimeException.class, () -> {
                        productService.updateProduct(productId, product);
                    });

            assertEquals("The product could not be found", exception.getMessage());

        }

    }

    @Nested
    class DeleteRequest {

        @Test
        void shouldDeleteAProductCorrectly() {

            var productId = 99L;

            doNothing()
                    .when(productRepository).deleteById(productIdArgumentCaptor.capture());

            productService.deleteProduct(productId);

            assertEquals(productIdArgumentCaptor.getValue(), productId);
            verify(productRepository, times(1)).deleteById(productId);

        }

    }

}
