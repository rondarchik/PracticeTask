package org.system.creditmanagementsystem.dto;

import lombok.Data;

import java.util.Date;
import java.util.UUID;

@Data
public class PaymentDto {
    private UUID id;
    private Double amount;
    private Date paymentDate;
    private String client;
    private UUID credit;
}
