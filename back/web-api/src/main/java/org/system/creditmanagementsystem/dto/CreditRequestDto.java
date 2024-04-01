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
public class CreditRequestDto {
    private UUID id;
    private UUID managerID;
    private UUID creditID;
    private Date dateOfRequest;
    private UUID statusID;
    private String rejectionMessage;
}
