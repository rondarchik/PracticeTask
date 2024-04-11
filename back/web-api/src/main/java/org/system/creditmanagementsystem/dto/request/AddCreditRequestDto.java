package org.system.creditmanagementsystem.dto.request;

import lombok.Data;
import org.system.creditmanagementsystem.entity.Credit;
import org.system.creditmanagementsystem.entity.RequestStatus;
import org.system.creditmanagementsystem.entity.User;

import java.util.Date;

@Data
public class AddCreditRequestDto {
    private User manager;
    private Credit credit;
    private Date dateOfRequest;
    private RequestStatus status;
    private String rejectionMessage;
}
