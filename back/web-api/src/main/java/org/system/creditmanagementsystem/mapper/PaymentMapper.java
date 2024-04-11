package org.system.creditmanagementsystem.mapper;

import org.mapstruct.Mapper;
import org.system.creditmanagementsystem.dto.payment.AddPaymentDto;
import org.system.creditmanagementsystem.dto.payment.GetPaymentDto;
import org.system.creditmanagementsystem.entity.Credit;
import org.system.creditmanagementsystem.entity.Payment;

import java.util.UUID;

@Mapper(componentModel = "spring")
public interface PaymentMapper {
    GetPaymentDto toDto(Payment payment);
    Payment fromDto(AddPaymentDto paymentDto);

    default UUID mapCredit(Credit credit) {
        return credit.getId();
    }
}
