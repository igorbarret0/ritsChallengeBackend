package com.backendChallenge.ritsBackendChallenge.factory.clientFactory;

import com.backendChallenge.ritsBackendChallenge.entities.client.Client;

public class ClientFactory {

    public static Client build() {

        return new Client("name", "email", "phone", "address");
    }

}
