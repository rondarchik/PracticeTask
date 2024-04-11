package org.system.creditmanagementsystem.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.system.creditmanagementsystem.dto.request.AddCreditRequestDto;
import org.system.creditmanagementsystem.dto.request.GetCreditRequestDto;
import org.system.creditmanagementsystem.dto.request.UpdateCreditRequestDto;
import org.system.creditmanagementsystem.service.CreditRequestService;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/credit_requests")
public class CreditRequestController {
    private final CreditRequestService creditRequestService;

    @Autowired
    public CreditRequestController(CreditRequestService creditRequestService) {
        this.creditRequestService = creditRequestService;
    }

    @GetMapping
    public ResponseEntity<List<GetCreditRequestDto>> getAllCreditRequests() {
        return new ResponseEntity<>(creditRequestService.getAllCreditRequests(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<GetCreditRequestDto> getCreditRequestById(@PathVariable UUID id) {
        return new ResponseEntity<>(creditRequestService.getCreditRequestById(id), HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity<GetCreditRequestDto> addCreditRequest(@RequestBody AddCreditRequestDto creditRequest) {
        return new ResponseEntity<>(creditRequestService.addCreditRequest(creditRequest), HttpStatus.CREATED);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<GetCreditRequestDto> updateCreditRequest(@RequestBody UpdateCreditRequestDto creditRequest, @PathVariable UUID id) {
        return new ResponseEntity<>(creditRequestService.updateCreditRequest(creditRequest, id), HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Object> removeCreditRequestById(@PathVariable UUID id) {
        creditRequestService.removeCreditRequestById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
