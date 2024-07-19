package com.backendChallenge.ritsBackendChallenge.controller;

import com.backendChallenge.ritsBackendChallenge.entities.request.dto.RequestDto;
import com.backendChallenge.ritsBackendChallenge.entities.request.dto.RequestProductDto;
import com.backendChallenge.ritsBackendChallenge.entities.request.dto.ResponseRequestDto;
import com.backendChallenge.ritsBackendChallenge.service.RequestService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/request")
public class RequestController {

    private RequestService requestService;

    public RequestController(RequestService requestService) {
        this.requestService = requestService;
    }

    @PostMapping("/{clientId}")
    public ResponseEntity<Void> makeAnRequest(@PathVariable(name = "clientId") Long clientId,
                                              @RequestBody RequestProductDto requestProductDto) {

        requestService.makeAnRequest(clientId, requestProductDto);

        return ResponseEntity.ok().build();

    }


    @GetMapping("/{clientId}")
    public ResponseEntity<ResponseRequestDto> getRequests(@PathVariable(name = "clientId") Long id) {

        var response = requestService.getRequests(id);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @DeleteMapping("/{clientId}")
    public ResponseEntity<Void> deleteRequest(@PathVariable(name = "clientId") Long clientId) {

        requestService.deleteRequest(clientId);

        return ResponseEntity.noContent().build();

    }

    @GetMapping
    public ResponseEntity<List<RequestDto>> getAllRequests() {

        var response = requestService.getAllRequests();

        return new ResponseEntity<>(response, HttpStatus.OK);

    }

}
