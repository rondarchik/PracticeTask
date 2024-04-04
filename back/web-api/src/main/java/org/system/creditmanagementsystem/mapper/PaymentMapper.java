package org.system.creditmanagementsystem.mapper;

import org.mapstruct.Mapper;
import org.system.creditmanagementsystem.dto.PaymentDto;
import org.system.creditmanagementsystem.entity.Payment;

@Mapper(componentModel = "spring")
public interface PaymentMapper {
    PaymentDto toDto(Payment payment);
    Payment fromDto(PaymentDto paymentDto);
}
