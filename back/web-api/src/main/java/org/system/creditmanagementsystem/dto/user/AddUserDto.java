package org.system.creditmanagementsystem.dto.user;

import lombok.Data;

import java.util.Date;

@Data
public class AddUserDto {
    private String name;
    private String surname;
    private String email;
    private Date birthDate;
    private String passwordHash;
}