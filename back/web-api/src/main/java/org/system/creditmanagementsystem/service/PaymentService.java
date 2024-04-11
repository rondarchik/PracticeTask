package org.system.creditmanagementsystem.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.system.creditmanagementsystem.dto.payment.AddPaymentDto;
import org.system.creditmanagementsystem.dto.payment.GetPaymentDto;
import org.system.creditmanagementsystem.dto.payment.UpdatePaymentDto;
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

    public List<GetPaymentDto> getAllPayments() {
        List<Payment> payments = paymentRepository.findAll();
        return payments.stream().map(payment -> {
            GetPaymentDto paymentDto = paymentMapper.toDto(payment);
            String user = payment.getUser().getName() +
                    " " +
                    payment.getUser().getSurname();
            paymentDto.setClient(user);
            paymentDto.setCredit(payment.getCredit().getId());
            return paymentDto;
        }).toList();
    }


    public GetPaymentDto getPaymentById(UUID id) {
        Payment payment = paymentRepository.findById(id).orElseThrow(() -> new NotFoundException(NOT_FOUND_MESSAGE));
        return paymentMapper.toDto(payment);
    }


    public GetPaymentDto addPayment(AddPaymentDto paymentDto) {
        Payment payment = paymentMapper.fromDto(paymentDto);
        paymentRepository.save(payment);
        return paymentMapper.toDto(payment);
    }

    public GetPaymentDto updatePayment(UpdatePaymentDto paymentDto, UUID id) {
        Payment existingPayment = paymentRepository.findById(id).orElseThrow(() -> new NotFoundException(NOT_FOUND_MESSAGE));
        if (paymentDto.getAmount() != null) {
            existingPayment.setAmount(paymentDto.getAmount());
        }
        if (paymentDto.getPaymentDate() != null) {
            existingPayment.setPaymentDate(paymentDto.getPaymentDate());
        }
        if (paymentDto.getClient() != null) {
            existingPayment.setUser(paymentDto.getClient());
        }
        if (paymentDto.getCredit() != null) {
            existingPayment.setCredit(paymentDto.getCredit());
        }
        paymentRepository.save(existingPayment);
        return paymentMapper.toDto(existingPayment);
    }

    public void removePaymentById(UUID id) {
        Optional<Payment> existingPayment = paymentRepository.findById(id);

        if (existingPayment.isEmpty()) {
            throw new NotFoundException(NOT_FOUND_MESSAGE);
        }

        paymentRepository.deleteById(id);
    }
}
