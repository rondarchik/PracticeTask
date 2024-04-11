package org.system.creditmanagementsystem.dto.status;

import lombok.Data;

import java.util.UUID;

@Data
public class GetRequestStatusDto {
    private UUID id;
    private String status;
}
