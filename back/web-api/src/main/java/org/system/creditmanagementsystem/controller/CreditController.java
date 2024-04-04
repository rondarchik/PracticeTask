package org.system.creditmanagementsystem.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.system.creditmanagementsystem.dto.CreditDto;
import org.system.creditmanagementsystem.service.CreditService;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/credit")
public class CreditController {
    private final CreditService creditService;

    @Autowired
    public CreditController(CreditService creditService) {
        this.creditService = creditService;
    }

    @GetMapping("/type/{typeId}")
    public List<CreditDto> getStatusRequests(@PathVariable UUID typeId) {
        return creditService.getTypeCredits(typeId);
    }

    @GetMapping
    public List<CreditDto> getAllCredits() {
        return creditService.getAllCredits();
    }

    @GetMapping("/{id}")
    public CreditDto getCreditById(@PathVariable UUID id) {
        return creditService.getCreditById(id);
    }

    @PostMapping("/add")
    public CreditDto addCredit(CreditDto credit) {
        return creditService.addCredit(credit);
    }

    @PutMapping("/update/{id}")
    public CreditDto updateCredit(CreditDto credit, @PathVariable UUID id) {
        return creditService.updateCredit(credit, id);
    }

    @DeleteMapping("/delete/{id}")
    public void removeCreditById(@PathVariable UUID id) {
        creditService.removeCreditById(id);
    }
}
