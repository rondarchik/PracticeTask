package org.system.creditmanagementsystem.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.system.creditmanagementsystem.dto.credit.AddCreditDto;
import org.system.creditmanagementsystem.dto.credit.GetCreditDto;
import org.system.creditmanagementsystem.dto.credit.UpdateCreditDto;
import org.system.creditmanagementsystem.service.CreditService;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/credits")
public class CreditController {
    private final CreditService creditService;

    @Autowired
    public CreditController(CreditService creditService) {
        this.creditService = creditService;
    }

    @GetMapping
    public ResponseEntity<List<GetCreditDto>> getAllCredits() {
        return new ResponseEntity<>(creditService.getAllCredits(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<GetCreditDto> getCreditById(@PathVariable UUID id) {
        return new ResponseEntity<>(creditService.getCreditById(id), HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity<GetCreditDto> addCredit(@RequestBody AddCreditDto credit) {
        return new ResponseEntity<>(creditService.addCredit(credit), HttpStatus.CREATED);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<GetCreditDto> updateCredit(@RequestBody UpdateCreditDto credit, @PathVariable UUID id) {
        return new ResponseEntity<>(creditService.updateCredit(credit, id), HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Object> removeCreditById(@PathVariable UUID id) {
        creditService.removeCreditById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
