package org.system.creditmanagementsystem.controller;

import org.springframework.web.bind.annotation.RestController;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.system.creditmanagementsystem.entity.CreditType;
import org.system.creditmanagementsystem.service.CreditTypeService;

import java.util.List;
import java.util.UUID;

@RestController
public class CreditTypeController {
    private final CreditTypeService creditTypeService;

    public CreditTypeController(CreditTypeService creditTypeService) {
        this.creditTypeService = creditTypeService;
    }

    @GetMapping("/credit_types")
    public ResponseEntity<List<CreditType>> getCreditTypes() {
        var creditTypes = creditTypeService.getCreditTypes();

        return new ResponseEntity<>(creditTypes, HttpStatus.OK);
    }

    @GetMapping("/credit_types/{id}")
    public ResponseEntity<CreditType> getCreditTypeById(@PathVariable UUID id) {
        var creditType = creditTypeService.getCreditTypeById(id);

        return new ResponseEntity<>(creditType, HttpStatus.OK);
    }

    @PostMapping("/credit_types")
    public ResponseEntity<Object> addCreditType(CreditType creditType) {
        creditTypeService.addCreditType(creditType);

        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping("/credit_types")
    public ResponseEntity<Object> updateCreditType(CreditType creditType) {
        creditTypeService.updateCreditType(creditType);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("/credit_types/{id}")
    public ResponseEntity<Object> removeCreditTypeById(@PathVariable UUID id) {
        creditTypeService.removeCreditTypeById(id);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
