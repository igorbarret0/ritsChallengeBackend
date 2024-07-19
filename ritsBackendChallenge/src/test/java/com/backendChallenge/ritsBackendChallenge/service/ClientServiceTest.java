package com.backendChallenge.ritsBackendChallenge.service;

import com.backendChallenge.ritsBackendChallenge.entities.client.Client;
import com.backendChallenge.ritsBackendChallenge.factory.clientFactory.ListClientFactory;
import com.backendChallenge.ritsBackendChallenge.factory.clientFactory.SaveClientDtoFactory;
import com.backendChallenge.ritsBackendChallenge.repository.ClientRepository;
import com.backendChallenge.ritsBackendChallenge.service.impl.ClientServiceImpl;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ClientServiceTest {

    @Mock
    ClientRepository clientRepository;

    @InjectMocks
    ClientServiceImpl clientService;

    @Nested
    class SaveClient {

        @Test
        void shouldRegisterCorrectlyAnClient() {

            var clientDto = SaveClientDtoFactory.build();
            var client = clientDto.toClient();

            doReturn(Optional.empty())
                    .when(clientRepository).findByEmail(anyString());

            doReturn(Optional.empty())
                    .when(clientRepository).findByPhone(anyString());

            doReturn(client)
                    .when(clientRepository).save(any(Client.class));

            var response = clientService.saveClient(clientDto);

            assertEquals(response.name(), clientDto.name());
            verify(clientRepository, times(1)).save(any(Client.class));
            verify(clientRepository, times(1)).findByEmail(clientDto.email());
            verify(clientRepository, times(1)).findByPhone(clientDto.phone());

        }

        @Test
        void shouldThrowExceptionWhenAnEmailAlreadyExists() {

            var clientDto = SaveClientDtoFactory.build();
            var client = clientDto.toClient();

            doReturn(Optional.of(client))
                    .when(clientRepository).findByEmail(clientDto.email());

            doReturn(Optional.empty())
                    .when(clientRepository).findByPhone(anyString());

            Exception exception = assertThrows(RuntimeException.class, () -> {
                clientService.saveClient(clientDto);
            });

            assertEquals("A client with these credentials is already registered", exception.getMessage());
        }

        @Test
        void shouldThrowExceptionWhenAnPhoneExists() {

            var clientDto = SaveClientDtoFactory.build();
            var client = clientDto.toClient();

            doReturn(Optional.empty())
                    .when(clientRepository).findByEmail(anyString());

            doReturn(Optional.of(client))
                    .when(clientRepository).findByPhone(clientDto.phone());

            Exception exception = assertThrows(RuntimeException.class, () -> {
                clientService.saveClient(clientDto);
            });

            assertEquals("A client with these credentials is already registered", exception.getMessage());
        }

    }

    @Nested
    class GetAllClients {

        @Test
        void shouldReturnAllClients() {

            var allClients = ListClientFactory.buildWithTwoClients();

            doReturn(allClients)
                    .when(clientRepository).findAll();

            var response = clientService.getAllClients();

            assertEquals(allClients.getFirst().getName(), response.getFirst().getName());
            verify(clientRepository, times(1)).findAll();
        }

    }

}
