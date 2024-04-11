package org.system.creditmanagementsystem.dto.type;

import lombok.Data;

@Data
public class AddCreditTypeDto {
    private String name;
    private Double creditAmount;
    private Double interestRate;
    private int termInMonths;
}
