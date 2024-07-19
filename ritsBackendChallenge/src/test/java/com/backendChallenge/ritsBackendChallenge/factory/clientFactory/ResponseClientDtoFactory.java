package com.backendChallenge.ritsBackendChallenge.factory.clientFactory;

import com.backendChallenge.ritsBackendChallenge.entities.client.dto.ResponseClientDto;

public class ResponseClientDtoFactory {

    public static ResponseClientDto build() {
        return new ResponseClientDto("name");
    }

}
