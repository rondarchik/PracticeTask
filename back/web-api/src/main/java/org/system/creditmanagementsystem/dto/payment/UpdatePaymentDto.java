package org.system.creditmanagementsystem.dto.payment;

import lombok.Data;
import org.system.creditmanagementsystem.entity.Credit;
import org.system.creditmanagementsystem.entity.User;

import java.util.Date;

@Data
public class UpdatePaymentDto {
    private Double amount;
    private Date paymentDate;
    private User client;
    private Credit credit;
}
