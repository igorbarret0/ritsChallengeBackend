package com.backendChallenge.ritsBackendChallenge.factory.clientFactory;

import com.backendChallenge.ritsBackendChallenge.entities.client.dto.SaveClientDto;

public class SaveClientDtoFactory {

    public static SaveClientDto build() {

        return new SaveClientDto("name", "email", "phone", "address");

    }

}
