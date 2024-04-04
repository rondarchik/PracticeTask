package org.system.creditmanagementsystem.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.system.creditmanagementsystem.dto.CreditDto;
import org.system.creditmanagementsystem.service.CreditService;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api")
public class CreditController {
    private final CreditService creditService;

    @Autowired
    public CreditController(CreditService creditService) {
        this.creditService = creditService;
    }

    @GetMapping("/credit_types/{typeId}")
    public List<CreditDto> getStatusRequests(@PathVariable UUID typeId) {
        return creditService.getTypeCredits(typeId);
    }

    @GetMapping("/credits")
    public List<CreditDto> getAllCredits() {
        return creditService.getAllCredits();
    }

    @GetMapping("/credits/{id}")
    public CreditDto getCreditById(@PathVariable UUID id) {
        return creditService.getCreditById(id);
    }

    @PostMapping("/credits/add")
    public CreditDto addCredit(CreditDto credit) {
        return creditService.addCredit(credit);
    }

    @PutMapping("/credits/update/{id}")
    public CreditDto updateCredit(CreditDto credit, @PathVariable UUID id) {
        return creditService.updateCredit(credit, id);
    }

    @DeleteMapping("/credits/delete/{id}")
    public void removeCreditById(@PathVariable UUID id) {
        creditService.removeCreditById(id);
    }
}
