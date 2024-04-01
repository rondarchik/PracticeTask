package org.system.creditmanagementsystem.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CreditTypeDto {
    private UUID id;
    private String name;
    private Double creditAmount;
    private Double interestRate;
    private int termInMonths;
}
