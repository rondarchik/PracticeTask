package org.system.creditmanagementsystem.dto.role;

import lombok.Data;

import java.util.UUID;

@Data
public class GetRoleDto {
    private UUID id;
    private String roleName;
}
