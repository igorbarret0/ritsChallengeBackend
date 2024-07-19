package com.backendChallenge.ritsBackendChallenge.factory.requestFactory;

import com.backendChallenge.ritsBackendChallenge.entities.request.dto.RequestDto;

import java.util.List;

public class ListRequestFactory {

    public static List<RequestDto> build() {

        var req1 = new RequestDto("request 1");
        var req2 = new RequestDto("request 2");

        return List.of(req1, req2);

    }

}
