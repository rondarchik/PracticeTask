package org.system.creditmanagementsystem.dto.credit;

import lombok.Data;
import org.system.creditmanagementsystem.entity.CreditType;
import org.system.creditmanagementsystem.entity.User;

import java.util.Date;

@Data
public class AddCreditDto {
    private User client;
    private CreditType creditType;
    private Double paidAmount;
    private Date startDate;
    private Date endDate;
    private Boolean status;
}
