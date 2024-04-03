package org.system.creditmanagementsystem.controller;

import org.springframework.web.bind.annotation.RestController;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.system.creditmanagementsystem.entity.CreditRequest;
import org.system.creditmanagementsystem.service.CreditRequestService;

import java.util.List;
import java.util.UUID;

@RestController
public class CreditRequestController {
    private final CreditRequestService creditRequestService;

    public CreditRequestController(CreditRequestService creditRequestService) {
        this.creditRequestService = creditRequestService;
    }

    @GetMapping("/credit_requests")
    public ResponseEntity<List<CreditRequest>> getCreditRequests() {
        var creditRequests = creditRequestService.getCreditRequests();

        return new ResponseEntity<>(creditRequests, HttpStatus.OK);
    }

    @GetMapping("/credit_requests/{id}")
    public ResponseEntity<CreditRequest> getCreditRequestById(@PathVariable UUID id) {
        var creditRequest = creditRequestService.getCreditRequestById(id);

        return new ResponseEntity<>(creditRequest, HttpStatus.OK);
    }

    @PostMapping("/credit_requests")
    public ResponseEntity<Object> addCreditRequest(CreditRequest creditRequest) {
        creditRequestService.addCreditRequest(creditRequest);

        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping("/credit_requests")
    public ResponseEntity<Object> updateCreditRequest(CreditRequest creditRequest) {
        creditRequestService.updateCreditRequest(creditRequest);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("/credit_requests/{id}")
    public ResponseEntity<Object> removeCreditRequestById(@PathVariable UUID id) {
        creditRequestService.removeCreditRequestById(id);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
