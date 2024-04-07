package org.system.creditmanagementsystem.dto;

import lombok.*;

import java.util.Date;
import java.util.Set;
import java.util.UUID;

@Data
public class UserDto {
    private UUID id;
    private String name;
    private String surname;
    private String email;
    private Date birthDate;
    private Set<String> roles;
}
