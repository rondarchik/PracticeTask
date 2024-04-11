package org.system.creditmanagementsystem.dto.payment;

import lombok.Data;

import java.util.Date;
import java.util.UUID;

@Data
public class GetPaymentDto {
    private UUID id;
    private Double amount;
    private Date paymentDate;
    private String client;
    private UUID credit;
}
