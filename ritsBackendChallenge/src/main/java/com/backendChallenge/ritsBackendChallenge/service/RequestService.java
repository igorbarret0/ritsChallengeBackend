package com.backendChallenge.ritsBackendChallenge.service;

import com.backendChallenge.ritsBackendChallenge.entities.request.dto.RequestDto;
import com.backendChallenge.ritsBackendChallenge.entities.request.dto.RequestProductDto;
import com.backendChallenge.ritsBackendChallenge.entities.request.dto.ResponseRequestDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface RequestService {

    void makeAnRequest(Long clientId, RequestProductDto requestProductDtoP);

    ResponseRequestDto getRequests(Long clientId);

    void deleteRequest(Long clientId);

    List<RequestDto> getAllRequests();

}
