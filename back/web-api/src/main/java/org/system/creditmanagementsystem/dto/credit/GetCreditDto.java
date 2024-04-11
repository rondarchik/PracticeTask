package org.system.creditmanagementsystem.dto.credit;

import lombok.Data;
import org.system.creditmanagementsystem.entity.CreditType;

import java.util.Date;
import java.util.UUID;

@Data
public class GetCreditDto {
    private UUID id;
    private String client;
    private CreditType creditType;
    private Double paidAmount;
    private Date startDate;
    private Date endDate;
    private Boolean status;
}
