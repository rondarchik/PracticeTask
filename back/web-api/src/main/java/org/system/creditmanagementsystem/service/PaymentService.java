package org.system.creditmanagementsystem.service;

import org.springframework.stereotype.Service;
import org.system.creditmanagementsystem.entity.Payment;
import org.system.creditmanagementsystem.exception.NotFoundException;
import org.system.creditmanagementsystem.repository.PaymentRepository;

import java.util.List;
import java.util.UUID;

@Service
public class PaymentService {
    private final PaymentRepository paymentRepository;
    private static final String NOT_FOUND_MESSAGE = "Such payment not found!";

    public PaymentService(PaymentRepository paymentRepository) {
        this.paymentRepository = paymentRepository;
    }

    public List<Payment> getPayments() {
        return paymentRepository.findAll();
    }

    public Payment getPaymentById(UUID id) {
        return paymentRepository.findById(id).orElseThrow(() -> new NotFoundException(NOT_FOUND_MESSAGE));
    }

    public void addPayment(Payment payment) {
        paymentRepository.save(payment);
    }

    public void updatePayment(Payment payment) {
        var existingPayment = paymentRepository.findById(payment.getId());

        if (existingPayment.isEmpty()) {
            throw new NotFoundException(NOT_FOUND_MESSAGE);
        }

        paymentRepository.save(payment);
    }

    public void removePaymentById(UUID id) {
        var existingPayment = paymentRepository.findById(id);

        if (existingPayment.isEmpty()) {
            throw new NotFoundException(NOT_FOUND_MESSAGE);
        }

        paymentRepository.deleteById(id);
    }
}
