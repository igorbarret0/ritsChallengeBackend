package com.backendChallenge.ritsBackendChallenge.factory.clientFactory;

import com.backendChallenge.ritsBackendChallenge.entities.client.Client;

import java.util.List;

public class ListClientFactory {

    public static List<Client> buildWithTwoClients() {

        var client1 = new Client("name", "email", "phone", "address");
        var client2 = new Client("name2", "email2", "phone2", "address2");

        return List.of(client1, client2);

    }

}
