package org.system.creditmanagementsystem.controller;

import org.springframework.web.bind.annotation.RestController;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.system.creditmanagementsystem.entity.Credit;
import org.system.creditmanagementsystem.service.CreditService;

import java.util.List;
import java.util.UUID;

@RestController
public class CreditController {
    private final CreditService creditService;

    public CreditController(CreditService creditService) {
        this.creditService = creditService;
    }

    @GetMapping("/credits")
    public ResponseEntity<List<Credit>> getCredits() {
        var credits = creditService.getCredits();

        return new ResponseEntity<>(credits, HttpStatus.OK);
    }

    @GetMapping("/credits/{id}")
    public ResponseEntity<Credit> getCreditById(@PathVariable UUID id) {
        var credit = creditService.getCreditById(id);

        return new ResponseEntity<>(credit, HttpStatus.OK);
    }

    @PostMapping("/credits")
    public ResponseEntity<Object> addCredit(Credit credit) {
        creditService.addCredit(credit);

        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping("/credits")
    public ResponseEntity<Object> updateCredit(Credit credit) {
        creditService.updateCredit(credit);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("/credits/{id}")
    public ResponseEntity<Object> removeCreditById(@PathVariable UUID id) {
        creditService.removeCreditById(id);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
