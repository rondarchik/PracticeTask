package org.system.creditmanagementsystem.dto;

import lombok.*;

import java.util.Date;
import java.util.UUID;

@Data
public class CreditRequestDto {
    private UUID id;
    private String manager;
    private UUID credit;
    private Date dateOfRequest;
    private String status;
    private String rejectionMessage;
}
