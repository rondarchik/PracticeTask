package org.system.creditmanagementsystem.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.system.creditmanagementsystem.dto.type.AddCreditTypeDto;
import org.system.creditmanagementsystem.dto.type.GetCreditTypeDto;
import org.system.creditmanagementsystem.dto.type.UpdateCreditTypeDto;
import org.system.creditmanagementsystem.service.CreditTypeService;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/credit_types")
public class CreditTypeController {
    private final CreditTypeService creditTypeService;

    @Autowired
    public CreditTypeController(CreditTypeService creditTypeService) {
        this.creditTypeService = creditTypeService;
    }

    @GetMapping
    public ResponseEntity<List<GetCreditTypeDto>> getAllCreditTypes() {
        return new ResponseEntity<>(creditTypeService.getAllCreditTypes(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<GetCreditTypeDto> getCreditTypeById(@PathVariable UUID id) {
        return new ResponseEntity<>(creditTypeService.getCreditTypeById(id), HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity<GetCreditTypeDto> addCreditType(@RequestBody AddCreditTypeDto creditType) {
        return new ResponseEntity<>(creditTypeService.addCreditType(creditType), HttpStatus.CREATED);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<GetCreditTypeDto> updateCreditType(@RequestBody UpdateCreditTypeDto creditType, @PathVariable UUID id) {
        return new ResponseEntity<>(creditTypeService.updateCreditType(creditType, id), HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Object> removeCreditTypeById(@PathVariable UUID id) {
        creditTypeService.removeCreditTypeById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
