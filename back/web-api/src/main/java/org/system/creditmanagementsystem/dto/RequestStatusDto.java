package org.system.creditmanagementsystem.dto;

import lombok.*;

import java.util.UUID;

@Data
public class RequestStatusDto {
    private UUID id;
    private String status;
}
