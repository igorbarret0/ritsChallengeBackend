package com.backendChallenge.ritsBackendChallenge.service.impl;

import com.backendChallenge.ritsBackendChallenge.entities.client.Client;
import com.backendChallenge.ritsBackendChallenge.entities.client.dto.ResponseClientDto;
import com.backendChallenge.ritsBackendChallenge.entities.client.dto.SaveClientDto;
import com.backendChallenge.ritsBackendChallenge.repository.ClientRepository;
import com.backendChallenge.ritsBackendChallenge.service.ClientService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClientServiceImpl implements ClientService {

    private ClientRepository clientRepository;

    public ClientServiceImpl(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }


    @Override
    public ResponseClientDto saveClient(SaveClientDto dto) {

        var emailExists = clientRepository.findByEmail(dto.email());
        var phoneExists = clientRepository.findByPhone(dto.phone());

        if (emailExists.isPresent() || phoneExists.isPresent()) {
            throw new RuntimeException("A client with these credentials is already registered");
        }

        var newClient = clientRepository.save(dto.toClient());

        return new ResponseClientDto(newClient.getName());

    }

    @Override
    public List<Client> getAllClients() {

        var allClients = clientRepository.findAll();

        return allClients;

    }
}
