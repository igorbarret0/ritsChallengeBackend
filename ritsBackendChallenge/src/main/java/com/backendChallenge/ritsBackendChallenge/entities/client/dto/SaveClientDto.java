package com.backendChallenge.ritsBackendChallenge.entities.client.dto;

import com.backendChallenge.ritsBackendChallenge.entities.client.Client;

public record SaveClientDto(
        String name,
        String email,
        String phone,
        String address
) {

    public Client toClient() {
        return new Client(name, email, phone, address);
    }

}
