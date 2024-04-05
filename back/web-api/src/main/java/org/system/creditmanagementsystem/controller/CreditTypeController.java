package org.system.creditmanagementsystem.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.system.creditmanagementsystem.dto.CreditTypeDto;
import org.system.creditmanagementsystem.service.CreditTypeService;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/credit_types")
@CrossOrigin("http://localhost:3000/")
public class CreditTypeController {
    private final CreditTypeService creditTypeService;

    @Autowired
    public CreditTypeController(CreditTypeService creditTypeService) {
        this.creditTypeService = creditTypeService;
    }

    @GetMapping
    public List<CreditTypeDto> getAllCreditTypes() {
        return creditTypeService.getAllCreditTypes();
    }

    @GetMapping("/{id}")
    public CreditTypeDto getCreditTypeById(@PathVariable UUID id) {
        return creditTypeService.getCreditTypeById(id);
    }

    @PostMapping("/add")
    public CreditTypeDto addCreditType(@RequestBody CreditTypeDto creditType) {
        return creditTypeService.addCreditType(creditType);
    }

    @PutMapping("/update/{id}")
    public CreditTypeDto updateCreditType(@RequestBody CreditTypeDto creditType, @PathVariable UUID id) {
        return creditTypeService.updateCreditType(creditType, id);
    }

    @DeleteMapping("/delete/{id}")
    public void removeCreditTypeById(@PathVariable UUID id) {
        creditTypeService.removeCreditTypeById(id);
    }
}
