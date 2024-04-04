package org.system.creditmanagementsystem.dto;

import lombok.*;

import java.util.Date;
import java.util.UUID;

@Data
public class CreditRequestDto {
    private UUID id;
    private UUID managerID;
    private UUID creditID;
    private Date dateOfRequest;
    private UUID statusID;
    private String rejectionMessage;
}
