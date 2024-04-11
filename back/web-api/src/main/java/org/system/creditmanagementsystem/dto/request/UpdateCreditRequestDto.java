package org.system.creditmanagementsystem.dto.request;

import lombok.Data;
import org.system.creditmanagementsystem.entity.RequestStatus;

import java.util.Date;
import java.util.UUID;

@Data
public class UpdateCreditRequestDto {
    private UUID id;
    private Date dateOfRequest;
    private RequestStatus status;
    private String rejectionMessage;
}
