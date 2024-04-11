package org.system.creditmanagementsystem.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.system.creditmanagementsystem.dto.payment.AddPaymentDto;
import org.system.creditmanagementsystem.dto.payment.GetPaymentDto;
import org.system.creditmanagementsystem.dto.payment.UpdatePaymentDto;
import org.system.creditmanagementsystem.service.PaymentService;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/payments")
public class PaymentController {
    private final PaymentService paymentService;

    @Autowired
    public PaymentController(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    @GetMapping
    public ResponseEntity<List<GetPaymentDto>> getAllPayments() {
         return new ResponseEntity<>(paymentService.getAllPayments(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<GetPaymentDto> getPaymentById(@PathVariable UUID id) {
        return new ResponseEntity<>(paymentService.getPaymentById(id), HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity<GetPaymentDto> addPayment(@RequestBody AddPaymentDto payment) {
        return new ResponseEntity<>(paymentService.addPayment(payment), HttpStatus.CREATED);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<GetPaymentDto> updatePayment(@RequestBody UpdatePaymentDto payment, @PathVariable UUID id) {
        return new ResponseEntity<>(paymentService.updatePayment(payment, id), HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Object> removePaymentById(@PathVariable UUID id) {
        paymentService.removePaymentById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
