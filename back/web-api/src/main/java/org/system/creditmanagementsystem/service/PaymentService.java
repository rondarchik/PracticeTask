package org.system.creditmanagementsystem.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.system.creditmanagementsystem.dto.PaymentDto;
import org.system.creditmanagementsystem.entity.Payment;
import org.system.creditmanagementsystem.exception.NotFoundException;
import org.system.creditmanagementsystem.mapper.PaymentMapper;
import org.system.creditmanagementsystem.repository.PaymentRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class PaymentService {
    private final PaymentRepository paymentRepository;
    private final PaymentMapper paymentMapper;
    private static final String NOT_FOUND_MESSAGE = "Such payment not found!";

    @Autowired
    public PaymentService(PaymentRepository paymentRepository, PaymentMapper paymentMapper) {
        this.paymentRepository = paymentRepository;
        this.paymentMapper = paymentMapper;
    }

    public List<PaymentDto> getAllPayments() {
        List<Payment> payments = paymentRepository.findAll();
        return payments.stream().map(payment -> {
            PaymentDto paymentDto = paymentMapper.toDto(payment);
            String user = payment.getUser().getName() +
                    " " +
                    payment.getUser().getSurname();
            paymentDto.setClient(user);
            paymentDto.setCredit(payment.getCredit().getId());
            return paymentDto;
        }).toList();
    }


    public PaymentDto getPaymentById(UUID id) {
        Payment payment = paymentRepository.findById(id).orElseThrow(() -> new NotFoundException(NOT_FOUND_MESSAGE));
        return paymentMapper.toDto(payment);
    }

    public List<PaymentDto> getCreditPayments(UUID creditId) {
        return paymentRepository.findByCreditId(creditId).stream().map(paymentMapper::toDto).toList();
    }

    public PaymentDto addPayment(PaymentDto paymentDto) {
        Payment payment = paymentMapper.fromDto(paymentDto);
        paymentRepository.save(payment);
        return paymentMapper.toDto(payment);
    }

    public PaymentDto updatePayment(PaymentDto paymentDto, UUID id) {
        Payment payment = paymentMapper.fromDto(paymentDto);
        Optional<Payment> existingPayment = paymentRepository.findById(id);

        if (existingPayment.isEmpty()) {
            throw new NotFoundException(NOT_FOUND_MESSAGE);
        }

        payment.setAmount(paymentDto.getAmount());
        payment.setPaymentDate(paymentDto.getPaymentDate());
        Payment updatedPayment = paymentRepository.save(payment);
        return paymentMapper.toDto(updatedPayment);
    }

    public void removePaymentById(UUID id) {
        Optional<Payment> existingPayment = paymentRepository.findById(id);

        if (existingPayment.isEmpty()) {
            throw new NotFoundException(NOT_FOUND_MESSAGE);
        }

        paymentRepository.deleteById(id);
    }
}
