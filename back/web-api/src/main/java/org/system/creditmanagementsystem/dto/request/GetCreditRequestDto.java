package org.system.creditmanagementsystem.dto.request;

import lombok.Data;

import java.util.Date;
import java.util.UUID;

@Data
public class GetCreditRequestDto {
    private UUID id;
    private String manager;
    private UUID credit;
    private Date dateOfRequest;
    private String status;
    private String rejectionMessage;
}