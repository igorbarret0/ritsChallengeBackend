package com.backendChallenge.ritsBackendChallenge.controller;

import com.backendChallenge.ritsBackendChallenge.entities.client.Client;
import com.backendChallenge.ritsBackendChallenge.entities.client.dto.ResponseClientDto;
import com.backendChallenge.ritsBackendChallenge.entities.client.dto.SaveClientDto;
import com.backendChallenge.ritsBackendChallenge.service.ClientService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/client")
public class ClientController {

    private ClientService clientService;

    public ClientController(ClientService clientService) {
        this.clientService = clientService;
    }

    @PostMapping
    public ResponseEntity<ResponseClientDto> saveClient(@RequestBody SaveClientDto dto) {

        var newClient = clientService.saveClient(dto);

        return new ResponseEntity<>(newClient, HttpStatus.OK);

    }

    @GetMapping
    public ResponseEntity<List<Client>> getAllClients() {

        var response = clientService.getAllClients();

        return new ResponseEntity<>(response, HttpStatus.OK);

    }

}
