package com.backendChallenge.ritsBackendChallenge.entities.request.dto;

import java.util.List;

public record ResponseRequestDto(
        String clientName,
        List<RequestDto> myRequests
) {
}
