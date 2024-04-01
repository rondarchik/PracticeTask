package org.system.creditmanagementsystem.model;

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
@Table(name="role")
public class Role {
    @Id
    @GeneratedValue
    @Column(name="id")
    private UUID id;

    @Column(name="role_name")
    private String roleName;
}
