package com.backendChallenge.ritsBackendChallenge.entities.request.dto;

import java.util.List;

public record RequestProductDto(
        List<RequestDto> requests
) {
}
