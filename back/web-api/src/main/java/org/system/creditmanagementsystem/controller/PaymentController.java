package org.system.creditmanagementsystem.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.system.creditmanagementsystem.dto.PaymentDto;
import org.system.creditmanagementsystem.service.PaymentService;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/payments")
@CrossOrigin("http://localhost:3000/")
public class PaymentController {
    private final PaymentService paymentService;

    @Autowired
    public PaymentController(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    @GetMapping("/credit/{creditId}")
    public List<PaymentDto> getCreditPayments(@PathVariable UUID creditId) {
        return paymentService.getCreditPayments(creditId);
    }

    @GetMapping
    public List<PaymentDto> getAllPayments() {
        return paymentService.getAllPayments();
    }

    @GetMapping("/{id}")
    public PaymentDto getPaymentById(@PathVariable UUID id) {
        return paymentService.getPaymentById(id);
    }

    @PostMapping("/add")
    public PaymentDto addPayment(@RequestBody PaymentDto payment) {
        return paymentService.addPayment(payment);
    }

    @PutMapping("/update/{id}")
    public PaymentDto updatePayment(@RequestBody PaymentDto payment, @PathVariable UUID id) {
        return paymentService.updatePayment(payment, id);
    }

    @DeleteMapping("/delete/{id}")
    public void removePaymentById(@PathVariable UUID id) {
        paymentService.removePaymentById(id);
    }
}
