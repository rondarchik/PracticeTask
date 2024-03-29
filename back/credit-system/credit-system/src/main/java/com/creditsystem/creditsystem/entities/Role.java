package com.creditsystem.creditsystem.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "Roles")
public class Role {
    @Id
    @Column(name = "id")
    protected UUID id;

    @Column(name = "role_name", unique = true)
    protected String roleName;

}
