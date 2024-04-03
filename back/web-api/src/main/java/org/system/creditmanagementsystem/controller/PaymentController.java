package org.system.creditmanagementsystem.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import org.springframework.web.bind.annotation.*;
import org.system.creditmanagementsystem.entity.Payment;
import org.system.creditmanagementsystem.service.PaymentService;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api")
public class PaymentController {
    private final PaymentService paymentService;

    @Autowired
    public PaymentController(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    @GetMapping("/credits/{creditId}")
    public List<Payment> getCreditPayments(@PathVariable UUID creditId) {
        return paymentService.getCreditPayments(creditId);
    }

    @GetMapping("/payments")
    public List<Payment> getAllPayments() {
        return paymentService.getAllPayments();
    }

    @GetMapping("/payments/{id}")
    public Payment getPaymentById(@PathVariable UUID id) {
        return paymentService.getPaymentById(id);
    }

    @PostMapping("/payments/add")
    public Payment addPayment(@RequestBody Payment payment) {
        return paymentService.addPayment(payment);
    }

    @PutMapping("/payments/update")
    public Payment updatePayment(@RequestBody Payment payment) {
        return paymentService.updatePayment(payment);
    }

    @DeleteMapping("/payments/delete/{id}")
    public void removePaymentById(@PathVariable UUID id) {
        paymentService.removePaymentById(id);
    }
}
