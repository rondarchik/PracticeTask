package org.system.creditmanagementsystem.mapper;

import org.mapstruct.Mapper;
import org.system.creditmanagementsystem.dto.PaymentDto;
import org.system.creditmanagementsystem.entity.Credit;
import org.system.creditmanagementsystem.entity.Payment;

import java.util.UUID;

@Mapper(componentModel = "spring")
public interface PaymentMapper {
    PaymentDto toDto(Payment payment);
    Payment fromDto(PaymentDto paymentDto);

    default Payment mapPayment(UUID id) {
        return null;
    }

    default UUID mapCredit(Credit credit) {
        return credit.getId();
    }

    default Credit mapToCredit(UUID creditId) {
        return null;
    }
}
