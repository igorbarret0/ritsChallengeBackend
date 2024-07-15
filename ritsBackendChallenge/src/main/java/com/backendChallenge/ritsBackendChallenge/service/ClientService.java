package com.backendChallenge.ritsBackendChallenge.service;

import com.backendChallenge.ritsBackendChallenge.entities.client.Client;
import com.backendChallenge.ritsBackendChallenge.entities.client.dto.ResponseClientDto;
import com.backendChallenge.ritsBackendChallenge.entities.client.dto.SaveClientDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ClientService {

    ResponseClientDto saveClient(SaveClientDto dto);

    List<Client> getAllClients();

}
