package org.system.creditmanagementsystem.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.system.creditmanagementsystem.dto.CreditRequestDto;
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

    @GetMapping("/status/{statusId}")
    public List<CreditRequestDto> getStatusRequests(@PathVariable UUID statusId) {
        return creditRequestService.getStatusRequests(statusId);
    }

    @GetMapping
    public List<CreditRequestDto> getAllCreditRequests() {
        return creditRequestService.getAllCreditRequests();
    }

    @GetMapping("/{id}")
    public CreditRequestDto getCreditRequestById(@PathVariable UUID id) {
        return creditRequestService.getCreditRequestById(id);
    }

    @PostMapping("/add")
    public CreditRequestDto addCreditRequest(@RequestBody CreditRequestDto creditRequest) {
        return creditRequestService.addCreditRequest(creditRequest);
    }

    @PutMapping("/update/{id}")
    public CreditRequestDto updateCreditRequest(@RequestBody CreditRequestDto creditRequest, @PathVariable UUID id) {
        return creditRequestService.updateCreditRequest(creditRequest, id);
    }

    @DeleteMapping("/delete/{id}")
    public void removeCreditRequestById(@PathVariable UUID id) {
        creditRequestService.removeCreditRequestById(id);
    }
}
