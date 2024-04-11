package org.system.creditmanagementsystem.dto.type;

import lombok.Data;

import java.util.UUID;

@Data
public class UpdateCreditTypeDto {
    private UUID id;
    private String name;
    private Double creditAmount;
    private Double interestRate;
    private int termInMonths;
}
