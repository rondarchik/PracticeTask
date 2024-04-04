package org.system.creditmanagementsystem.dto;

import lombok.*;

import java.util.UUID;

@Data
public class CreditTypeDto {
    private UUID id;
    private String name;
    private Double creditAmount;
    private Double interestRate;
    private int termInMonths;
}
