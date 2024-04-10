package org.system.creditmanagementsystem.dto.role;

import lombok.Data;

import java.util.UUID;

@Data
public class UpdateRoleDto {
    private UUID id;
    private String roleName;
}
