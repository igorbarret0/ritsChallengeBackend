package com.backendChallenge.ritsBackendChallenge.controller;


import com.backendChallenge.ritsBackendChallenge.entities.request.dto.RequestProductDto;
import com.backendChallenge.ritsBackendChallenge.factory.requestFactory.ListRequestFactory;
import com.backendChallenge.ritsBackendChallenge.factory.requestFactory.RequestFactory;
import com.backendChallenge.ritsBackendChallenge.service.RequestService;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class RequestControllerTest {

    @Mock
    RequestService requestService;

    @InjectMocks
    RequestController requestController;

    @Captor
    ArgumentCaptor<Long> clientIdArgumentCaptor;

    @Captor
    ArgumentCaptor<RequestProductDto> requestProductDtoArgumentCaptor;

    @Nested
    class MakeAnRequest {

        @Test
        void shouldReturnHttpOk() {

            var clientId = 1L;
            var requests = RequestFactory.buildWithOneRequest();

            doNothing()
                    .when(requestService).makeAnRequest(anyLong(), any());

            var response = requestController.makeAnRequest(clientId, requests);

            assertEquals(HttpStatus.OK, response.getStatusCode());
            verify(requestService, times(1)).makeAnRequest(clientId, requests);

        }

        @Test
        void shouldPassCorrectParametersToService() {

            var clientId = 1L;
            var requests = RequestFactory.buildWithOneRequest();

            doNothing()
                    .when(requestService).makeAnRequest(clientIdArgumentCaptor.capture(), requestProductDtoArgumentCaptor.capture());

            var response = requestController.makeAnRequest(clientId, requests);

            assertEquals(clientIdArgumentCaptor.getValue(), clientId);
            assertEquals(requestProductDtoArgumentCaptor.getValue().requests().getFirst().name(), requests.requests().getFirst().name());
        }


    }

    @Nested
    class GetRequests {

        @Test
        void shouldReturnHttpOk() {

            var clientId = 1L;

            doReturn(RequestFactory.buildResponseRequestDto())
                    .when(requestService).getRequests(anyLong());

            var response = requestController.getRequests(clientId);

            assertEquals(HttpStatus.OK, response.getStatusCode());
            verify(requestService, times(1)).getRequests(clientId);

        }

        @Test
        void shouldPassCorrectParametersToService() {

            var clientId = 1L;

            doReturn(RequestFactory.buildResponseRequestDto())
                    .when(requestService).getRequests(clientIdArgumentCaptor.capture());

            var response = requestController.getRequests(clientId);

            assertEquals(clientIdArgumentCaptor.getValue(), clientId);
        }

        @Test
        void shouldReturnCorrectResponseBody() {

            var clientId = 1L;
            var expectedResponse = RequestFactory.buildResponseRequestDto();

            doReturn(expectedResponse)
                    .when(requestService).getRequests(anyLong());

            var response = requestController.getRequests(clientId);

            assertNotNull(response);
            assertNotNull(response.getBody());
            assertNotNull(response.getBody().clientName());
            assertNotNull(response.getBody().myRequests());

            assertEquals(response.getBody().clientName(), expectedResponse.clientName());
            assertEquals(response.getBody().myRequests().getFirst().name(),
                    expectedResponse.myRequests().getFirst().name());

        }

    }

    @Nested
    class deleteRequest {

        @Test
        void shouldReturnHttpNoContent() {

            var clientId = 1L;

            doNothing()
                    .when(requestService).deleteRequest(anyLong());

            var response = requestController.deleteRequest(clientId);

            assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
            verify(requestService, times(1)).deleteRequest(clientId);

        }

        @Test
        void shouldPassTheCorrectParametersToService() {

            var clientId = 1L;

            doNothing()
                    .when(requestService).deleteRequest(clientIdArgumentCaptor.capture());


            var response = requestController.deleteRequest(clientId);

            assertEquals(clientIdArgumentCaptor.getValue(), clientId);
        }

    }

    @Nested
    class GetAllRequests {

        @Test
        void shouldReturnHttpOk() {

            doReturn(ListRequestFactory.build())
                    .when(requestService).getAllRequests();

            var response = requestController.getAllRequests();

            assertEquals(HttpStatus.OK, response.getStatusCode());
            verify(requestService, times(1)).getAllRequests();

        }

        @Test
        void shouldReturnCorrectResponseBody() {

            var requestList = ListRequestFactory.build();

            doReturn(ListRequestFactory.build())
                    .when(requestService).getAllRequests();

            var response = requestController.getAllRequests();

            assertEquals(response.getBody().getFirst().name(), requestList.getFirst().name());
            assertEquals(response.getBody().getLast().name(), requestList.getLast().name());

        }

    }

}
