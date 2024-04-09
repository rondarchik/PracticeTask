package org.system.creditmanagementsystem.dto;

import lombok.*;

import java.util.Date;
import java.util.UUID;

@Data
public class CreditDto {
    private UUID id;
    private String client;
    private String creditType;
    private Double paidAmount;
    private Date startDate;
    private Date endDate;
    private Boolean status;
}
