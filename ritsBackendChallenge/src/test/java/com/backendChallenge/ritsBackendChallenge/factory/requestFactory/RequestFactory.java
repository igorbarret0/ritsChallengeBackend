package com.backendChallenge.ritsBackendChallenge.factory.requestFactory;

import com.backendChallenge.ritsBackendChallenge.entities.request.dto.RequestDto;
import com.backendChallenge.ritsBackendChallenge.entities.request.dto.RequestProductDto;
import com.backendChallenge.ritsBackendChallenge.entities.request.dto.ResponseRequestDto;

import java.util.List;

public class RequestFactory {

    public static RequestProductDto buildWithOneRequest() {

        var request1 = new RequestDto("product name");

        return new RequestProductDto(List.of(request1));

    }

    public static RequestProductDto buildWithTwoRequests() {

        var request1 = new RequestDto("product name");
        var request2 = new RequestDto("product name 2");

        return new RequestProductDto(List.of(request1, request2));


    }

    public static ResponseRequestDto buildResponseRequestDto() {

        return new ResponseRequestDto("client name", buildWithOneRequest().requests());

    }

}
