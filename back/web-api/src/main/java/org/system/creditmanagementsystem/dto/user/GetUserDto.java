package org.system.creditmanagementsystem.dto.user;

import lombok.Data;
import org.system.creditmanagementsystem.entity.Role;

import java.util.Date;
import java.util.Set;
import java.util.UUID;

@Data
public class GetUserDto {
    private UUID id;
    private String name;
    private String surname;
    private String email;
    private Date birthDate;
    private Set<Role> roles;
}
