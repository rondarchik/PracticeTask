package org.system.creditmanagementsystem.controller;

import org.springframework.web.bind.annotation.RestController;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.system.creditmanagementsystem.entity.Payment;
import org.system.creditmanagementsystem.service.PaymentService;

import java.util.List;
import java.util.UUID;

@RestController
public class PaymentController {
    private final PaymentService paymentService;

    public PaymentController(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    @GetMapping("/payments")
    public ResponseEntity<List<Payment>> getPayments() {
        var payments = paymentService.getPayments();

        return new ResponseEntity<>(payments, HttpStatus.OK);
    }

    @GetMapping("/payments/{id}")
    public ResponseEntity<Payment> getPaymentById(@PathVariable UUID id) {
        var payment = paymentService.getPaymentById(id);

        return new ResponseEntity<>(payment, HttpStatus.OK);
    }

    @PostMapping("/payments")
    public ResponseEntity<Object> addPayment(Payment payment) {
        paymentService.addPayment(payment);

        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping("/payments")
    public ResponseEntity<Object> updatePayment(Payment payment) {
        paymentService.updatePayment(payment);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("/payments/{id}")
    public ResponseEntity<Object> removePaymentById(@PathVariable UUID id) {
        paymentService.removePaymentById(id);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
