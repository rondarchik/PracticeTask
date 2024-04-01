package org.system.creditmanagementsystem.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CreditDto {
    private UUID id;
    private UUID clientID;
    private UUID creditTypeID;
    private Double paidAmount;
    private Date startDate;
    private Date endDate;
    private Boolean status;
}
