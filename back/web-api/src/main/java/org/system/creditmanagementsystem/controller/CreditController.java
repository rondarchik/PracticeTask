package org.system.creditmanagementsystem.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.system.creditmanagementsystem.entity.Credit;
import org.system.creditmanagementsystem.service.CreditService;

import java.util.List;
import java.util.UUID;

@RestController
public class CreditController {
    private final CreditService creditService;

    @Autowired
    public CreditController(CreditService creditService) {
        this.creditService = creditService;
    }

    @GetMapping("/credit_types/{typeId}")
    public List<Credit> getStatusRequests(@PathVariable UUID typeId) {
        return creditService.getTypeCredits(typeId);
    }

    @GetMapping("/credits")
    public List<Credit> getAllCredits() {
        return creditService.getAllCredits();
    }

    @GetMapping("/credits/{id}")
    public Credit getCreditById(@PathVariable UUID id) {
        return creditService.getCreditById(id);
    }

    @PostMapping("/credits/add")
    public Credit addCredit(Credit credit) {
        return creditService.addCredit(credit);
    }

    @PutMapping("/credits/update")
    public Credit updateCredit(Credit credit) {
        return creditService.updateCredit(credit);
    }

    @DeleteMapping("/credits/delete/{id}")
    public void removeCreditById(@PathVariable UUID id) {
        creditService.removeCreditById(id);
    }
}
