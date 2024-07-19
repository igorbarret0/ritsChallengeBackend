package com.backendChallenge.ritsBackendChallenge.service.impl;

import com.backendChallenge.ritsBackendChallenge.entities.product.Product;
import com.backendChallenge.ritsBackendChallenge.entities.productRequest.ProductRequest;
import com.backendChallenge.ritsBackendChallenge.entities.productRequest.ProductRequestId;
import com.backendChallenge.ritsBackendChallenge.entities.request.Request;
import com.backendChallenge.ritsBackendChallenge.entities.request.RequestStatus;
import com.backendChallenge.ritsBackendChallenge.entities.request.dto.RequestDto;
import com.backendChallenge.ritsBackendChallenge.entities.request.dto.RequestProductDto;
import com.backendChallenge.ritsBackendChallenge.entities.request.dto.ResponseRequestDto;
import com.backendChallenge.ritsBackendChallenge.repository.ClientRepository;
import com.backendChallenge.ritsBackendChallenge.repository.ProductRepository;
import com.backendChallenge.ritsBackendChallenge.repository.ProductRequestRepository;
import com.backendChallenge.ritsBackendChallenge.repository.RequestRepository;
import com.backendChallenge.ritsBackendChallenge.service.RequestService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class RequestServiceImpl implements RequestService {

    private ClientRepository clientRepository;
    private RequestRepository requestRepository;
    private ProductRepository productRepository;
    private ProductRequestRepository productRequestRepository;

    public RequestServiceImpl(ClientRepository clientRepository, RequestRepository requestRepository,
                              ProductRepository productRepository, ProductRequestRepository productRequestRepository) {
        this.clientRepository = clientRepository;
        this.requestRepository = requestRepository;
        this.productRepository = productRepository;
        this.productRequestRepository = productRequestRepository;
    }

    @Override
    public void makeAnRequest(Long clientId, RequestProductDto requestProductDto) {

        var client = clientRepository.findById(clientId)
                .orElseThrow(() -> new RuntimeException("A client could not be found"));

        var request = new Request(client);

        requestRepository.save(request);

        List<Product> myRequestedProducts = getRequestedProducts(requestProductDto);

        for (Product product : myRequestedProducts) {

            var id = new ProductRequestId(product.getId(), request.getId());

            var productRequestEntity = new ProductRequest(
                    id,
                    product,
                    request
            );

            productRequestRepository.save(productRequestEntity);

        }

    }

    @Override
    public ResponseRequestDto getRequests(Long clientId) {

        var client = clientRepository.findById(clientId)
                .orElseThrow(() -> new RuntimeException("Client not found"));

        var allProductsRequests = productRequestRepository.findAll();

        List<RequestDto> myOrders = new ArrayList<>();

        for (ProductRequest productRequest : allProductsRequests) {
            if (productRequest.getRequest().getId().equals(client.getId())) {
                myOrders.add(new RequestDto(productRequest.getProduct().getName()));
            }
        }

        return new ResponseRequestDto(client.getName(), myOrders);

    }

    @Override
    public void deleteRequest(Long clientId) {

        var request = requestRepository.findById(clientId)
                .orElseThrow(() -> new RuntimeException("This client don`t make an request"));

       request.setRequestStatus(RequestStatus.CANCELADO);
       requestRepository.save(request);

    }

    @Override
    public List<RequestDto> getAllRequests() {


        var allProductsRequests = productRequestRepository.findAll();

        List<RequestDto> allOrders = new ArrayList<>();

        for (ProductRequest productRequest : allProductsRequests) {

            if (productRequest.getRequest().getRequestStatus() != RequestStatus.CANCELADO &&
                productRequest.getRequest().getRequestStatus() != RequestStatus.ENTREGUE) {

                allOrders.add(new RequestDto(productRequest.getProduct().getName()));
            }
        }


        return allOrders;

    }


    private List<Product> getRequestedProducts(RequestProductDto requestProductDto) {

        List<Product> list = new ArrayList<>();

        for (RequestDto requestDto : requestProductDto.requests()) {

            var productFound = productRepository.findByName(requestDto.name());
            list.add(productFound);
        }

        return list;
    }

}
