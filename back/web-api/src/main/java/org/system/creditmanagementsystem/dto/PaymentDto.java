package org.system.creditmanagementsystem.dto;

import lombok.*;

import java.util.Date;
import java.util.UUID;

@Data
public class PaymentDto {
    private UUID id;
    private UUID clientID;
    private UUID creditID;
    private Double amount;
    private Date paymentDate;
}
