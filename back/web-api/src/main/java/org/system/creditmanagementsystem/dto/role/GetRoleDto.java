package org.system.creditmanagementsystem.dto.role;

import lombok.Data;
import org.system.creditmanagementsystem.entity.User;

import java.util.Set;
import java.util.UUID;

@Data
public class GetRoleDto {
    private UUID id;
    private String roleName;
    private Set<User> users;
}
