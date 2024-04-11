package org.system.creditmanagementsystem.dto.status;

import lombok.Data;

import java.util.UUID;

@Data
public class UpdateRequestStatusDto {
    private UUID id;
    private String status;
}
