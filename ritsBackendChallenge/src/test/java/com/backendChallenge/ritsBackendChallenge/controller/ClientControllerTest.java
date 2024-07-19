package com.backendChallenge.ritsBackendChallenge.controller;

import com.backendChallenge.ritsBackendChallenge.entities.client.dto.SaveClientDto;
import com.backendChallenge.ritsBackendChallenge.factory.clientFactory.ListClientFactory;
import com.backendChallenge.ritsBackendChallenge.factory.clientFactory.ResponseClientDtoFactory;
import com.backendChallenge.ritsBackendChallenge.factory.clientFactory.SaveClientDtoFactory;
import com.backendChallenge.ritsBackendChallenge.service.ClientService;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ClientControllerTest {

    @Mock
    ClientService clientService;

    @InjectMocks
    ClientController clientController;

    @Captor
    ArgumentCaptor<SaveClientDto> saveClientDtoCaptor;

    @Nested
    class SaveClient {

        @Test
        void shouldReturnHttpOk() {

            var saveClientDto = SaveClientDtoFactory.build();

            doReturn(ResponseClientDtoFactory.build())
                    .when(clientService).saveClient(any());

            var response = clientController.saveClient(saveClientDto);

            assertEquals(HttpStatusCode.valueOf(200), response.getStatusCode());

        }

        @Test
        void shouldPassCorrectParametersToService() {

            var dto = SaveClientDtoFactory.build();

            doReturn(ResponseClientDtoFactory.build())
                    .when(clientService).saveClient(saveClientDtoCaptor.capture());

            var response = clientController.saveClient(dto);

            assertEquals(dto.name(), saveClientDtoCaptor.getValue().name());
            assertEquals(dto.email(), saveClientDtoCaptor.getValue().email());
            assertEquals(dto.phone(), saveClientDtoCaptor.getValue().phone());
            assertEquals(dto.address(), saveClientDtoCaptor.getValue().address());

        }

        @Test
        void shouldReturnCorrectResponseBody() {

            var saveClientDto = SaveClientDtoFactory.build();

            doReturn(ResponseClientDtoFactory.build())
                    .when(clientService).saveClient(any());


            var response = clientController.saveClient(saveClientDto);

            assertNotNull(response);
            assertNotNull(response.getBody());
            assertNotNull(response.getBody().name());
            assertEquals(saveClientDto.name(), response.getBody().name());
        }

    }

    @Nested
    class getAllClients {

        @Test
        void shouldReturnHttpOk() {

            var clients = ListClientFactory.buildWithTwoClients();

            doReturn(clients)
                    .when(clientService).getAllClients();

            var response = clientController.getAllClients();

            assertEquals(HttpStatus.OK, response.getStatusCode());
        }

        @Test
        void shouldReturnCorrectResponseBody() {

            var allClients = ListClientFactory.buildWithTwoClients();

            doReturn(allClients).when(clientService).getAllClients();

            var response = clientController.getAllClients();

            assertEquals(allClients.getFirst().getName(), response.getBody().getFirst().getName());
            assertEquals(allClients.getFirst().getEmail(), response.getBody().getFirst().getEmail());
            assertEquals(allClients.getFirst().getPhone(), response.getBody().getFirst().getPhone());
            assertEquals(allClients.getFirst().getAddress(), response.getBody().getFirst().getAddress());

            assertEquals(allClients.getLast().getName(), response.getBody().getLast().getName());
            assertEquals(allClients.getLast().getEmail(), response.getBody().getLast().getEmail());
            assertEquals(allClients.getLast().getPhone(), response.getBody().getLast().getPhone());
            assertEquals(allClients.getLast().getAddress(), response.getBody().getLast().getAddress());


        }

    }

}
