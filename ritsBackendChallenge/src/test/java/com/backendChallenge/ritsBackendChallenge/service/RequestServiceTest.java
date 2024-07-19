package com.backendChallenge.ritsBackendChallenge.service;


import com.backendChallenge.ritsBackendChallenge.entities.productRequest.ProductRequest;
import com.backendChallenge.ritsBackendChallenge.entities.productRequest.ProductRequestId;
import com.backendChallenge.ritsBackendChallenge.entities.request.Request;
import com.backendChallenge.ritsBackendChallenge.factory.clientFactory.ClientFactory;
import com.backendChallenge.ritsBackendChallenge.factory.productFactory.ProductFactory;
import com.backendChallenge.ritsBackendChallenge.factory.requestFactory.ListRequestFactory;
import com.backendChallenge.ritsBackendChallenge.factory.requestFactory.RequestFactory;
import com.backendChallenge.ritsBackendChallenge.repository.ClientRepository;
import com.backendChallenge.ritsBackendChallenge.repository.ProductRepository;
import com.backendChallenge.ritsBackendChallenge.repository.ProductRequestRepository;
import com.backendChallenge.ritsBackendChallenge.repository.RequestRepository;
import com.backendChallenge.ritsBackendChallenge.service.impl.RequestServiceImpl;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.jdbc.JdbcUpdateAffectedIncorrectNumberOfRowsException;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
public class RequestServiceTest {

    @Mock
    RequestRepository requestRepository;

    @Mock
    ProductRepository productRepository;

    @Mock
    ClientRepository clientRepository;

    @Mock
    ProductRequestRepository productRequestRepository;

    @InjectMocks
    RequestServiceImpl requestService;

    @Nested
    class MakeAnRequest {


    }



}
